package net.jayde.util.file.musicFileTag;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.flac.FlacTag;
import org.jaudiotagger.tag.mp4.Mp4Tag;

public class ReadMusicTagUtil {

    private static Logger logger = LogManager.getLogger(ReadMusicTagUtil.class.getName());
    static String allpath = "D:\\music\\java\\";
    static String filename_alacall = allpath+"ab.m4a";
    static String filename_alacone = allpath+"alac_one.m4a";
    static String filename_flacall = allpath+"flac_all.flac";
    static String filename_flacone = allpath+"flac_one.flac";
    static String filename_apeall = allpath+"ape_all.ape";
    static String filename_apeone = allpath+"ape_one.ape";
    static String filename_aacone = allpath+"aac_one.m4a";
    static String filename_mp3one = allpath+"mp3_one.mp3";
    static String filename_wavone = allpath+"wav_one.wav";

    public static void main(String[] args) throws ReadOnlyFileException, CannotReadException, TagException, InvalidAudioFrameException, IOException {
//        FlacTag flacTag1 = ReadOfFlac.getFlacTag(new File(filename_flacall));
//        FlacTag flacTag2 = ReadOfFlac.getFlacTag(new File(filename_flacone));
        File file = new File(filename_alacall);
        Mp4Tag mp4Tag= ReadOfAlac.getAlacTag(file);
    }


}
