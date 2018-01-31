package net.jayde.app.music.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.flac.FlacTag;

public class ReadCueFromFlac {

  private static Logger logger = LogManager.getLogger(ReadCueFromFlac.class.getName());

  public TagField ContainCueTag(File flacFile) {

    TagField cueField = null;
    AudioFile audioFile = null;
    try {
      audioFile = AudioFileIO.read(flacFile);
      AudioHeader audioHeader = audioFile.getAudioHeader();
      Tag tag = audioFile.getTag();
      if (audioHeader.getEncodingType().contains("FLAC")) {
        FlacTag flacTag = (FlacTag) tag;
        System.out.println("   " + flacTag.getFirstField(FieldKey.TRACK_TOTAL));

        cueField = flacTag.getFirstField("CUESHEET");
        System.out.println("   cue");
      }
    } catch (CannotReadException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (TagException e) {
      e.printStackTrace();
    } catch (ReadOnlyFileException e) {
      e.printStackTrace();
    } catch (InvalidAudioFrameException e) {
      e.printStackTrace();
    }
    return cueField;
  }

  public boolean ExistCueFile(String cueFileName) {
    File cueFile = new File(cueFileName);
    if (cueFile.exists())
      return true;
    else
      return false;
  }

  public boolean CreateCueFile(File flacFile) {
    boolean result = false;
    return result;
  }

  public void CycleFlacInFolder(String path) {
    File pathFile = null;
    pathFile = new File(path);
    File[] files = pathFile.listFiles();
    for (File file : files) {
      String filename = file.getAbsolutePath();
      if (file.isFile() && filename.toLowerCase().endsWith(".flac")) {
        int length = filename.length();
        String cueFileName = filename.substring(0, length - 5) + ".cue";
        System.out.println(file.getName());
        System.out.println(cueFileName);
        TagField tagField = ContainCueTag(file);
        if (tagField != null) {
          System.out.println("   contain CueTag");
          if (ExistCueFile(cueFileName)) {
            System.out.println("remove:" + cueFileName);
          } else {
            File cueFile = new File(cueFileName);
            try {
              cueFile.createNewFile();
              cueFile.setWritable(true);
              FileWriter writer = new FileWriter(cueFile, true);
              // System.out.print("32bit result:" + DigestUtils.md5Hex() + "\n");
              // System.out.print("16bit result:"
              // + DigestUtils.md5Hex(input).substring(8, 24) + "\n");

              String cueText = tagField.toString();
              String cdimage = "FILE \"CDImage.wav\" WAVE";
              String flacNmae = "FILE \"" + file.getName() + "\" WAVE";
              cueText = cueText.replaceAll(cdimage, flacNmae);
              System.out.println(cueText);
              writer.write(cueText);
              writer.flush();
              writer.close();
            } catch (IOException e) {
              e.printStackTrace();
            }

          }
          // System.out.println(tagField.toString());
        }
      }
      if (file.isDirectory()) {
        CycleFlacInFolder(file.getAbsolutePath());
      }
    }
  }

  public static void main(String[] args) {
    ReadCueFromFlac readCueFromFlac = new ReadCueFromFlac();
    readCueFromFlac.CycleFlacInFolder("D:\\音频\\音频-FLAC合集");
  }
}
