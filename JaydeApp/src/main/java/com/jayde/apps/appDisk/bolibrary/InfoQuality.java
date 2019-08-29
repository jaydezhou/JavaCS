package com.jayde.apps.appDisk.bolibrary;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.tag.flac.FlacTag;
import org.jaudiotagger.tag.vorbiscomment.VorbisCommentTag;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appDisk.bolibrary
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-08-13 11:56
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-08-13 11:56
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class InfoQuality {
    public static int MUSIC_FILE_QUALITY = 1;
    public static int MUSIC_CD_QUALITY = 2;
    public static int MUSIC_ALBUM_QUALITY = 3;
    public static int MUSIC_SINGER_QUALITY = 4;
    public static int MUSIC_GROUP_QUALITY = 5;
    public static int MUSIC_TYPE_QUALITY = 6;
    public static int MUSIC_LIBRARY_QUALITY = 7;
    public static int MUSIC_LIBRARYSET_QUALITY = 8;
    public static int MUSIC_OTHERFILE_QUALITY = 0;


    public static int MUSIC_MEDIA_MP3 = 1;
    public static int MUSIC_MEDIA_HIFI = 2;
    public static int MUSIC_MEDIA_51 = 3;
    public static int MUSIC_MEDIA_DSD = 4;


    public InfoQuality(File file) {
        try {
            AudioFile f = AudioFileIO.read(file);
            FlacTag tag = (FlacTag) f.getTag();
            System.out.println("FlacTag:::" + tag);
            VorbisCommentTag vorbisTag = tag.getVorbisCommentTag();
            System.out.println("VorbisCommentTag::::" + vorbisTag);
            List images = tag.getImages();
            System.out.println("Image:::" + images);

            AudioHeader audioHeader = f.getAudioHeader();
            System.out.println(audioHeader);
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        String musicFileText = "/Users/mac/Desktop/5.1dts/04. 天黑黑.flac";
        musicFileText = "/Volumes/MusicP5T/标准文件夹/【音频】/download/2。FLAC+5.1声道。古典/DTS 5.1声道/2017未分类/推荐欧美经典 加州旅馆等首首经典 1.27 无损5.1/05.Crazy.wav";
        File musicFile = new File(musicFileText);
        InfoQuality infoQuality = new InfoQuality(musicFile);
        try {
            System.out.println(new String("02. 但願人長久".getBytes("GB2312"), "GB2312").equals("02. 但願人長久"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
