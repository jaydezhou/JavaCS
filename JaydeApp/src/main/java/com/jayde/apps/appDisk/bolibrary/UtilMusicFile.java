package com.jayde.apps.appDisk.bolibrary;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.flac.FlacTag;
import org.jaudiotagger.tag.id3.*;

import java.io.File;
import java.io.IOException;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appDisk.bolibrary
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-08-14 16:37
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-08-14 16:37
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class UtilMusicFile {

    static String[] MUSIC_FILE_TYPES = {"MP3", "FLAC", "DTS", "DFF", "WAV", "APE", "M4A", "ACC"};
    static String[] MUSIC_LYRICS_TYPES = {"LRC", "SRT", "UTF", "KSC", "SSA", "ASS", "SMI"};

    public static boolean isMusicFile(File file) {
        if (file.exists() && file.length() > 0) {
            String filenameupper = file.getName().toUpperCase();
            for (String suffix : MUSIC_FILE_TYPES) {
                if (filenameupper.endsWith(suffix)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public static boolean isMusicLyricsFile(File file) {
        if (file.exists() && file.length() > 0) {
            String filenameupper = file.getName().toUpperCase();
            for (String suffix : MUSIC_LYRICS_TYPES) {
                if (filenameupper.endsWith(suffix)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public static AudioHeader getMusicHeader(File file) {
        try {
            AudioFile f = AudioFileIO.read(file);
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

    public static String getMusicTagType(File file) {
        return null;
    }

    public static CommonTag getMusicFlacTag(File file) {
        CommonTag tag = null;
        try {
            AudioFile f = AudioFileIO.read(file);
            FlacTag flacTag = (FlacTag) f.getTag();
            tag = new CommonTag(file.getName(),flacTag);
        } catch (Exception e) {

        }
        return tag;
    }

    public static CommonTag getMusicMp3Tag(File file) {
        CommonTag tag = null;
        try {
            MP3File f = new MP3File(file);
            if (f.hasID3v1Tag()) {
                ID3v1Tag v1Tag = f.getID3v1Tag();
                tag = new CommonTag(file.getName(),v1Tag);
                System.out.println("v1Tag:" + v1Tag);
            }
            if (f.hasID3v2Tag()) {
                AbstractID3v2Tag v2tag = f.getID3v2Tag();
                System.out.println("v2tag:" + v2tag);
                if (v2tag instanceof ID3v24Tag) {
                    ID3v24Tag v24Tag = (ID3v24Tag) v2tag;
                    tag = new CommonTag(file.getName(),v24Tag);
                } else if (v2tag instanceof ID3v23Tag) {
                    System.out.println("ID3v23Tag");
                    ID3v23Tag v23Tag = (ID3v23Tag) v2tag;
                    tag = new CommonTag(file.getName(),v23Tag);
                } else if (v2tag instanceof ID3v22Tag) {
                    System.out.println("ID3v22Tag");
                    ID3v22Tag v22Tag = (ID3v22Tag) v2tag;
                    tag = new CommonTag(file.getName(),v22Tag);
                }
            }
        } catch (Exception e) {

        }
        return tag;
    }


}
