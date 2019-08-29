package com.jayde.apps.appDisk.bolibrary;

import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.flac.FlacTag;
import org.jaudiotagger.tag.id3.ID3v1Tag;
import org.jaudiotagger.tag.vorbiscomment.VorbisCommentFieldKey;
import org.jaudiotagger.tag.vorbiscomment.VorbisCommentTag;

import java.io.File;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appDisk.bolibrary
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-08-21 16:56
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-08-21 16:56
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class InfoQualityMusic {
    static long SIZE_100K = 1024 * 1024;
    static long SIZE_300M = 1024 * 1024 * 300;

    static float SCORE_SIZE = 0.1f;
    static float SCORE_NAME = 0.1f;
    static float SCORE_ENCODE = 0.1f;
    static float SCORE_HEADER = 0.1f;
    static float SCORE_TAG = 0.6f;
    static int SCORE_IMAGE = 10;
    //    static int SCORE_TAG = 50;
    static int SCORE_TAG_PER = 4;
    //    static int SCORE_TAG_ALBUM = 4;
//    static int SCORE_TAG_ALBUMARTIST = 4;
//    static int SCORE_TAG__TITLE = 4;
//    static int SCORE_TAG__ARTIST = 4;
//    static int SCORE_TAG__TRACK = 4;
//    static int SCORE_TAG__TRACKTOTAL = 4;
//    static int SCORE_TAG__DISC = 4;
//    static int SCORE_TAG__DISCTOTAL = 4;
//    static int SCORE_TAG__DATE = 4;
//    static int SCORE_TAG__GENRE = 4;
//    static int SCORE_TAG__COMMENT = 4;
//    static int SCORE_TAG__LYRICS = 4;
    static int SCORE_TAG__OTHER = 2;

    static String CHARSET = "GB2312";

    static VorbisCommentFieldKey[] keys = {
            VorbisCommentFieldKey.ALBUM,
            VorbisCommentFieldKey.ALBUMARTIST,
            VorbisCommentFieldKey.TITLE,
            VorbisCommentFieldKey.ARTIST,
            VorbisCommentFieldKey.TRACKNUMBER,
            VorbisCommentFieldKey.TRACKTOTAL,
            VorbisCommentFieldKey.DISCNUMBER,
            VorbisCommentFieldKey.DISCTOTAL,
            VorbisCommentFieldKey.DATE,
            VorbisCommentFieldKey.GENRE,
            VorbisCommentFieldKey.COMMENT,
            VorbisCommentFieldKey.LYRICS};

    public static float vertifyMusicFile(File file) {
        float score = 0f;
        String filename = file.getName();
        String suffix = getFileSuffix(file);

        //Size【10】
        if (file.length() >= SIZE_100K) {
            score += 100f * SCORE_SIZE;
            System.out.println(filename + "(Size):+" + 100f * SCORE_SIZE + "=" + score);
        }

        //Name【10】
        score += 100f * SCORE_NAME;
        System.out.println(filename + "(Name):+" + 100f * SCORE_NAME + "=" + score);

        //AudioHeader【10】
        AudioHeader audioHeader = UtilMusicFile.getMusicHeader(file);
        if (audioHeader != null) {
            score += 100f * SCORE_HEADER;
            System.out.println(filename + "(Header):+" + 100f * SCORE_HEADER + "=" + score);
        }

        switch (suffix) {
            case "FLAC": {
                System.out.println("FLAC音频文件：" + filename);

                //Encode【10】
                score += 100f * SCORE_ENCODE;
                System.out.println(filename + "(Encode):+" + 100f * SCORE_ENCODE + "=" + score);

                //Tag【50】
                String tagType = UtilMusicFile.getMusicTagType(file);
                CommonTag commonFlacTag = UtilMusicFile.getMusicFlacTag(file);
                float flacscore = validateCommonTag(commonFlacTag);
                System.out.println("flacscore:" + flacscore);
                score = score + flacscore * SCORE_TAG;

                System.out.println(filename + "【value】:" + score);
                break;
            }
            case "MP3": {
                System.out.println("MP3音频文件：" + filename);

                //Encode【10】
                score += 100f * SCORE_ENCODE;
                System.out.println(filename + "(Encode):+" + 100f * SCORE_ENCODE + "=" + score);

                //Tag【50】
                String tagType = UtilMusicFile.getMusicTagType(file);
                CommonTag commonID3Tag = UtilMusicFile.getMusicMp3Tag(file);
                float id3score = validateCommonTag(commonID3Tag);
                System.out.println("id3score:" + id3score);
                score = score + id3score * SCORE_TAG;

                System.out.println(filename + "【value】:" + score);
            }
            default: {

            }
        }
        return score;
    }

    private static float validateCommonTag(CommonTag commonTag) {
        String[] values = {
                commonTag.getALBUM(),
                commonTag.getALBUMARTIST(),
                commonTag.getTITLE(),
                commonTag.getARTIST(),
                commonTag.getTRACKNUMBER(),
                commonTag.getTRACKTOTAL(),
                commonTag.getDISCNUMBER(),
                commonTag.getDISCTOTAL(),
                commonTag.getDATE(),
                commonTag.getGENRE(),
                commonTag.getCOMMENT()
//                commonTag.getLANGUAGE()
//                commonTag.getLYRICS()
        };
        float SCORE_IMAGE = 10f;
        float SCORE_VALUE = (100f - SCORE_IMAGE) / (values.length);

        float score = 0f;
        float scoretotal = 0;

        System.out.println("filename:" + commonTag.getFilename());
        for (String value : values) {
            if (value != null && value.trim().length() > 0) {

                score = SCORE_VALUE;
                String trimvalue = value.trim();
                if (value.equals(trimvalue) == false) {
                    score = score - 1f;
                }
                try {
                    if (trimvalue.equals(new String(trimvalue.getBytes(CHARSET), CHARSET)) == false) {
                        score = score - 1f;
                    }
                } catch (Exception e) {

                }
                System.out.println("   tag:" + value + " =" + score);
                scoretotal = scoretotal + score;
            }
        }

        if (commonTag.getIMAGE()) {
            scoretotal = scoretotal + SCORE_IMAGE;
            System.out.println("   image:" + " =" + SCORE_IMAGE);
        }
        scoretotal = Math.round(scoretotal*100)/100f;
        System.out.println("values:" + scoretotal);
        return scoretotal;
    }

    private static int validateField(VorbisCommentFieldKey key, VorbisCommentTag tag, int totleScore) {
        int score = totleScore;
        String value = tag.getFirst(key);
        if (value == null || value.trim().length() == 0) {
            return 0;
        }
        String trimvalue = value.trim();
        if (value.equals(trimvalue) == false) {
            score--;
            System.out.println("key[" + key + "]" + value + " is trim error");
        }
        try {
            if (trimvalue.equals(new String(trimvalue.getBytes(CHARSET), CHARSET)) == false) {
                score--;
                System.out.println("key[" + key + "]" + value + " is charset error");
            }
        } catch (Exception e) {

        }
        return score;
    }

    private static int calcFlacTag(FlacTag flacTag) {
        int flacTagValue = 100;

        if (flacTag == null) {
            flacTagValue = 0;
            return flacTagValue;
        }

        if (flacTag.getImages() == null || flacTag.getImages().size() == 0) {
            flacTagValue -= 10;
        }

        VorbisCommentTag vTag = flacTag.getVorbisCommentTag();
        if (vTag == null) {
            flacTagValue -= 90;
        } else {
            System.out.println("-------------------------------------------");
            System.out.println("ALBUM:" + vTag.getFirst(VorbisCommentFieldKey.ALBUM));
            System.out.println("ALBUMARTIST:" + vTag.getFirst(VorbisCommentFieldKey.ALBUMARTIST));
            System.out.println("TITLE:" + vTag.getFirst(VorbisCommentFieldKey.TITLE));
            System.out.println("ARTIST:" + vTag.getFirst(VorbisCommentFieldKey.ARTIST));
            System.out.println("TRACKNUMBER:" + vTag.getFirst(VorbisCommentFieldKey.TRACKNUMBER));
            System.out.println("TRACKTOTAL:" + vTag.getFirst(VorbisCommentFieldKey.TRACKTOTAL));
            System.out.println("DESCRIPTION:" + vTag.getFirst(VorbisCommentFieldKey.DESCRIPTION));
            System.out.println("COMMENT:" + vTag.getFirst(VorbisCommentFieldKey.COMMENT));
            System.out.println("COMPOSER:" + vTag.getFirst(VorbisCommentFieldKey.COMPOSER));
            System.out.println("DATE:" + vTag.getFirst(VorbisCommentFieldKey.DATE));
            System.out.println("LYRICS:" + vTag.getFirst(VorbisCommentFieldKey.LYRICS));
            System.out.println("LYRICIST:" + vTag.getFirst(VorbisCommentFieldKey.LYRICIST));
            System.out.println("COPYRIGHT:" + vTag.getFirst(VorbisCommentFieldKey.COPYRIGHT));
//            System.out.println(":"+vTag.getValue());
        }

        return flacTagValue;

    }

    public static String getFileSuffix(File file) {
        String filename = file.getName();
        if (filename.contains(".") == false) {
            return "";
        }
        String[] subs = filename.split("\\.");
        return subs[subs.length - 1].toUpperCase();
    }

    public static void main(String[] args) {
        String filename;
//        filename= "/Users/mac/Desktop/5.1dts/04. 天黑黑.flac";
//        InfoQualityMusic.vertifyMusicFile(new File(filename));
        filename = "/Users/mac/Desktop/5.1dts/阿宝-青藏高原.flac";
        InfoQualityMusic.vertifyMusicFile(new File(filename));
        filename = "/Users/mac/Desktop/5.1dts/张云雷-乾坤带.mp3";
        InfoQualityMusic.vertifyMusicFile(new File(filename));
        filename = "/Users/mac/Desktop/5.1dts/阿杜-惩罚.mp3";
        InfoQualityMusic.vertifyMusicFile(new File(filename));
    }
}
