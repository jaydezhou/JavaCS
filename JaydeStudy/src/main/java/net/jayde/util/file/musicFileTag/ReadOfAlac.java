package net.jayde.util.file.musicFileTag;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.logging.ErrorMessage;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.mp4.Mp4FieldKey;
import org.jaudiotagger.tag.mp4.Mp4Tag;

public class ReadOfAlac {

  private static Logger logger = LogManager.getLogger(ReadOfAlac.class.getName());

  public static Mp4Tag getAlacTag(File file)
      throws IOException, TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException {

    AudioFile f = AudioFileIO.read(file);
    Tag tag = f.getTag();

    Mp4Tag mp4tag = (Mp4Tag) tag;
    // System.out.println(flacTag);
    // System.out.println("-------------------------------------------");
    Iterator<TagField> listall = mp4tag.getFields();
    while (listall.hasNext()) {
      TagField tagField = listall.next();

      System.out.println(tagField.getId());
      System.out.println(mp4tag.getFirst(tagField.getId()));
      System.out.println("------------------");
    }
    System.out.println("ARTIST:"+mp4tag.getFirst(Mp4FieldKey.ARTIST));
    System.out.println("COMPOSER:"+mp4tag.getFirst(Mp4FieldKey.COMPOSER));
    System.out.println("LYRICS:"+mp4tag.getFirst(Mp4FieldKey.LYRICS));
    System.out.println("SINGLE_DISC_TRACK_NO:"+mp4tag.getFirst(Mp4FieldKey.SINGLE_DISC_TRACK_NO));
    return mp4tag;
  }
}
