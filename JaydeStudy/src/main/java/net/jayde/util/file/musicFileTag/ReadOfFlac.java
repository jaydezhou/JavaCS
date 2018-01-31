package net.jayde.util.file.musicFileTag;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.flac.FlacFileReader;
import org.jaudiotagger.audio.flac.FlacTagReader;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.flac.FlacTag;
import org.jaudiotagger.tag.images.Artwork;

public class ReadOfFlac {

  private static Logger logger = LogManager.getLogger(ReadOfFlac.class.getName());

  public static FlacTag getFlacTag(File file) throws IOException, TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException {
      AudioFile f = AudioFileIO.read(file);
      Tag tag = f.getTag();
      AudioHeader audioHeader= f.getAudioHeader();
      FlacTag flacTag = (FlacTag)tag;
//      System.out.println(flacTag);
//      System.out.println("-------------------------------------------");
      Iterator<TagField> listall =  flacTag.getFields();
     while(listall.hasNext()){
         TagField tagField = listall.next();

         System.out.println(tagField.getId());
         System.out.println(flacTag.getFirst(tagField.getId()));
         System.out.println("------------------");
     }
    List<TagField> listtfs =  flacTag.getFields("CUESHEET");
     for(TagField tf:listtfs){
         System.out.println(tf);
     }
      System.out.println(audioHeader);
      TagField cue = flacTag.getFirstField("CUESHEET");
      if(cue!=null) {
          System.out.println(cue.toString());
      }

      List<Artwork> lista = tag.getArtworkList();
      for(Artwork artwork:lista){
          System.out.println(artwork);
      }
    return flacTag;
  }

}
