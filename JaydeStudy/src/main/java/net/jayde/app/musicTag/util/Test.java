package net.jayde.app.musicTag.util;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.flac.FlacTag;

public class Test {

  private static Logger logger = LogManager.getLogger(Test.class.getName());

  public static void main(String[] args) {
     String filename = "D:\\music\\java\\flac《all》[CD10](流行篇).flac";
//    String filename = "D:\\music\\java\\flac_all.cue";
//    String filename = "D:\\music\\java\\flac_one.flac";
    File file = new File(filename);
    AudioFile audioFile = null;
    try {
      audioFile = AudioFileIO.read(file);
//      System.out.println(audioFile);
      AudioHeader audioHeader = audioFile.getAudioHeader();
//      System.out.println(audioHeader);
//      System.out.println(audioHeader.getEncodingType());
//      System.out.println(audioHeader.getEncodingType().contains("FLAC"));
//      System.out.println(audioFile.getExt());
      Tag tag = audioFile.getTag();
      if(audioHeader.getEncodingType().contains("FLAC")){
        FlacTag flacTag = (FlacTag)tag;
        System.out.println(flacTag.getFieldCount());
//        System.out.println(flacTag);
        Iterator<TagField> iterator = flacTag.getFields();
        System.out.println("--------------------------------");
        while(iterator.hasNext()){
          TagField tagField = iterator.next();
          System.out.println(tagField.getId()+":   "+tagField.toString());
          System.out.println("*******");
        }
      }
    } catch (CannotReadException e) {
      System.out.println(e.getLocalizedMessage());
      return;
    } catch (IOException e) {
      System.out.println(e.getLocalizedMessage());
      return;
    } catch (TagException e) {
      System.out.println(e.getLocalizedMessage());
      return;
    } catch (ReadOnlyFileException e) {
      System.out.println(e.getLocalizedMessage());
      return;
    } catch (InvalidAudioFrameException e) {
      System.out.println(e.getLocalizedMessage());
      return;
    }
  }
}
