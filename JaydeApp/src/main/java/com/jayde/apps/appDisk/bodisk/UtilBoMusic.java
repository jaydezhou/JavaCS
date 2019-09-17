package com.jayde.apps.appDisk.bodisk;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.flac.FlacTag;
import org.jaudiotagger.tag.id3.*;

import java.io.File;
import java.io.IOException;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appDisk.bodisk
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-09-17 17:03
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-09-17 17:03
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class UtilBoMusic {
    public static AudioHeader readHeader(File inputFile, String suffix) {
        //库包不支持
        switch (suffix) {
            case "AAC":
            case "ACC":
            case "ALAC":
            case "APE":
            case "DIFF":
//            case "DSF":
            case "DST":
            case "DTS":
//            case "FLAC":
//            case "MP3":
//            case "OGG":
//            case "WAV":
                return null;
        }

        //库包不支持
        switch (suffix) {
            case "APE":
                return null;
        }

        try {
            AudioFile f = AudioFileIO.read(inputFile);
            AudioHeader audioHeader = f.getAudioHeader();
            return audioHeader;
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
        return null;
    }

    public static CommonTag readTag(File inputFile, String suffix) {
        //库包不支持
        switch (suffix) {
            case "AAC":
            case "ACC":
            case "ALAC":
            case "APE":
            case "DIFF":
//            case "DSF":
            case "DST":
            case "DTS":
//            case "FLAC":
//            case "MP3":
//            case "OGG":
//            case "WAV":
                return null;
        }

        CommonTag tag = null;

        if (suffix.equals("FLAC")) {
            try {
                AudioFile f = AudioFileIO.read(inputFile);
                FlacTag flacTag = (FlacTag) f.getTag();
                tag = new CommonTag(inputFile.getName(), flacTag);
            } catch (Exception e) {
                tag = null;
            }
        }

        if(suffix.equals("MP3")){
            try {
                MP3File f = new MP3File(inputFile);
                if (f.hasID3v1Tag()) {
                    ID3v1Tag v1Tag = f.getID3v1Tag();
                    tag = new CommonTag(inputFile.getName(), v1Tag);
                    System.out.println("v1Tag:" + v1Tag);
                }
                if (f.hasID3v2Tag()) {
                    AbstractID3v2Tag v2tag = f.getID3v2Tag();
                    System.out.println("v2tag:" + v2tag);
                    if (v2tag instanceof ID3v24Tag) {
                        ID3v24Tag v24Tag = (ID3v24Tag) v2tag;
                        tag = new CommonTag(inputFile.getName(), v24Tag);
                    } else if (v2tag instanceof ID3v23Tag) {
                        System.out.println("ID3v23Tag");
                        ID3v23Tag v23Tag = (ID3v23Tag) v2tag;
                        tag = new CommonTag(inputFile.getName(), v23Tag);
                    } else if (v2tag instanceof ID3v22Tag) {
                        System.out.println("ID3v22Tag");
                        ID3v22Tag v22Tag = (ID3v22Tag) v2tag;
                        tag = new CommonTag(inputFile.getName(), v22Tag);
                    }
                }
            } catch (Exception e) {

            }
        }
        return tag;
    }
}
