package com.jayde.apps.appDisk.bolibrary;

import org.jaudiotagger.audio.wav.WavTag;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.datatype.Artwork;
import org.jaudiotagger.tag.flac.FlacTag;
import org.jaudiotagger.tag.id3.*;
import org.jaudiotagger.tag.vorbiscomment.VorbisCommentFieldKey;
import org.jaudiotagger.tag.vorbiscomment.VorbisCommentTag;

import java.util.List;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appDisk.bolibrary
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-08-26 23:48
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-08-26 23:48
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class CommonTag {
    String tagType;
    String ALBUM;
    String ALBUMARTIST;
    String TITLE;
    String ARTIST;
    String TRACKNUMBER;
    String TRACKTOTAL;
    String DISCNUMBER;
    String DISCTOTAL;
    String DATE;
    String GENRE;
    String COMMENT;
    String LYRICS;
    String LANGUAGE;
    boolean IMAGE;

    String filename;

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public String getALBUM() {
        return ALBUM;
    }

    public void setALBUM(String ALBUM) {
        this.ALBUM = ALBUM;
        if (this.ALBUM == null) this.ALBUM = "";
    }

    public String getALBUMARTIST() {
        return ALBUMARTIST;
    }

    public void setALBUMARTIST(String ALBUMARTIST) {
        this.ALBUMARTIST = ALBUMARTIST;
        if (this.ALBUMARTIST == null) this.ALBUMARTIST = "";
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
        if (this.TITLE == null) this.TITLE = "";
    }

    public String getARTIST() {
        return ARTIST;
    }

    public void setARTIST(String ARTIST) {
        this.ARTIST = ARTIST;
        if (this.ARTIST == null) this.ARTIST = "";
    }

    public String getTRACKNUMBER() {
        return TRACKNUMBER;
    }

    public void setTRACKNUMBER(String TRACKNUMBER) {
        this.TRACKNUMBER = TRACKNUMBER;
        if (this.TRACKNUMBER == null) this.TRACKNUMBER = "";
    }

    public String getTRACKTOTAL() {
        return TRACKTOTAL;
    }

    public void setTRACKTOTAL(String TRACKTOTAL) {
        this.TRACKTOTAL = TRACKTOTAL;
        if (this.TRACKTOTAL == null) this.TRACKTOTAL = "";
    }

    public String getDISCNUMBER() {
        return DISCNUMBER;
    }

    public void setDISCNUMBER(String DISCNUMBER) {
        this.DISCNUMBER = DISCNUMBER;
        if (this.DISCNUMBER == null) this.DISCNUMBER = "";
    }

    public String getDISCTOTAL() {
        return DISCTOTAL;
    }

    public void setDISCTOTAL(String DISCTOTAL) {
        this.DISCTOTAL = DISCTOTAL;
        if (this.DISCTOTAL == null) this.DISCTOTAL = "";
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String DATE) {
        this.DATE = DATE;
        if (this.DATE == null) this.DATE = "";
    }

    public String getGENRE() {
        return GENRE;
    }

    public void setGENRE(String GENRE) {
        this.GENRE = GENRE;
        if (this.GENRE == null) this.GENRE = "";
    }

    public String getCOMMENT() {
        return COMMENT;
    }

    public void setCOMMENT(String COMMENT) {
        this.COMMENT = COMMENT;
        if (this.COMMENT == null) this.COMMENT = "";
    }

    public String getLYRICS() {
        return LYRICS;
    }

    public void setLYRICS(String LYRICS) {
        this.LYRICS = LYRICS;
        if (this.LYRICS == null) this.LYRICS = "";
    }

    public boolean getIMAGE() {
        return IMAGE;
    }

    public void setIMAGE(boolean IMAGE) {
        this.IMAGE = IMAGE;
    }

    public String getLANGUAGE() {
        return LANGUAGE;
    }

    public void setLANGUAGE(String LANGUAGE) {
        this.LANGUAGE = LANGUAGE;
        if (this.LANGUAGE == null) this.LANGUAGE = "";
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public CommonTag(String inputFilename, FlacTag flacTag) {
        filename = inputFilename;
        VorbisCommentTag vorbisCommentTag = flacTag.getVorbisCommentTag();
        setALBUM(vorbisCommentTag.getFirst(VorbisCommentFieldKey.ALBUM));
        setALBUMARTIST(vorbisCommentTag.getFirst(VorbisCommentFieldKey.ALBUMARTIST));
        setTITLE(vorbisCommentTag.getFirst(VorbisCommentFieldKey.TITLE));
        setARTIST(vorbisCommentTag.getFirst(VorbisCommentFieldKey.ARTIST));
        setTRACKNUMBER(vorbisCommentTag.getFirst(VorbisCommentFieldKey.TRACKNUMBER));
        setTRACKTOTAL(vorbisCommentTag.getFirst(VorbisCommentFieldKey.TRACKTOTAL));
        setDISCNUMBER(vorbisCommentTag.getFirst(VorbisCommentFieldKey.DISCNUMBER));
        setDISCTOTAL(vorbisCommentTag.getFirst(VorbisCommentFieldKey.DISCTOTAL));
        setDATE(vorbisCommentTag.getFirst(VorbisCommentFieldKey.DATE));
        setCOMMENT(vorbisCommentTag.getFirst(VorbisCommentFieldKey.GENRE));
        setGENRE(vorbisCommentTag.getFirst(VorbisCommentFieldKey.COMMENT));
        setLANGUAGE(vorbisCommentTag.getFirst(VorbisCommentFieldKey.LANGUAGE));
        setLYRICS(vorbisCommentTag.getFirst(VorbisCommentFieldKey.LYRICS));
        if (flacTag.getImages() != null && flacTag.getImages().size() > 0) {
            setIMAGE(true);
        }
    }

    public CommonTag(String inputFilename, ID3v1Tag v1Tag) {
        filename = inputFilename;
        setALBUM(v1Tag.getFirst(FieldKey.ALBUM));
        setALBUMARTIST(v1Tag.getFirst(FieldKey.ALBUM_ARTIST));
        setTITLE(v1Tag.getFirst(FieldKey.TITLE));
        setARTIST(v1Tag.getFirst(FieldKey.ARTIST));
        setTRACKNUMBER(v1Tag.getFirst(FieldKey.TRACK));
        setTRACKTOTAL(v1Tag.getFirst(FieldKey.TRACK_TOTAL));
        setDISCNUMBER(v1Tag.getFirst(FieldKey.DISC_NO));
        setDISCTOTAL(v1Tag.getFirst(FieldKey.DISC_TOTAL));
        setDATE(v1Tag.getFirst(FieldKey.YEAR));
        setGENRE(v1Tag.getFirst(FieldKey.GENRE));
        setCOMMENT(v1Tag.getFirst(FieldKey.COMMENT));
        setLANGUAGE(v1Tag.getFirst(FieldKey.LANGUAGE));
        setLYRICS(v1Tag.getFirst(FieldKey.LYRICS));
        List<Artwork> list = v1Tag.getArtworkList();
        if(list!=null && list.size()>0){
            setIMAGE(true);
        }else{
            setIMAGE(false);
        }
    }

    public CommonTag(String inputFilename, ID3v22Tag v22Tag) {
        filename = inputFilename;
        setALBUM(v22Tag.getFirst(FieldKey.ALBUM));
        setALBUMARTIST(v22Tag.getFirst(FieldKey.ALBUM_ARTIST));
        setTITLE(v22Tag.getFirst(FieldKey.TITLE));
        setARTIST(v22Tag.getFirst(FieldKey.ARTIST));
        setTRACKNUMBER(v22Tag.getFirst(FieldKey.TRACK));
        setTRACKTOTAL(v22Tag.getFirst(FieldKey.TRACK_TOTAL));
        setDISCNUMBER(v22Tag.getFirst(FieldKey.DISC_NO));
        setDISCTOTAL(v22Tag.getFirst(FieldKey.DISC_TOTAL));
        setDATE(v22Tag.getFirst(FieldKey.YEAR));
        setGENRE(v22Tag.getFirst(FieldKey.GENRE));
        setCOMMENT(v22Tag.getFirst(FieldKey.COMMENT));
        setLANGUAGE(v22Tag.getFirst(FieldKey.LANGUAGE));
        setLYRICS(v22Tag.getFirst(FieldKey.LYRICS));
        List<Artwork> list = v22Tag.getArtworkList();
        if(list!=null && list.size()>0){
            setIMAGE(true);
        }else{
            setIMAGE(false);
        }
    }

    public CommonTag(String inputFilename, ID3v23Tag v23Tag) {
        filename = inputFilename;
        setALBUM(v23Tag.getFirst(FieldKey.ALBUM));
        setALBUMARTIST(v23Tag.getFirst(FieldKey.ALBUM_ARTIST));
        setTITLE(v23Tag.getFirst(FieldKey.TITLE));
        setARTIST(v23Tag.getFirst(FieldKey.ARTIST));
        setTRACKNUMBER(v23Tag.getFirst(FieldKey.TRACK));
        setTRACKTOTAL(v23Tag.getFirst(FieldKey.TRACK_TOTAL));
        setDISCNUMBER(v23Tag.getFirst(FieldKey.DISC_NO));
        setDISCTOTAL(v23Tag.getFirst(FieldKey.DISC_TOTAL));
        setDATE(v23Tag.getFirst(FieldKey.YEAR));
        setGENRE(v23Tag.getFirst(FieldKey.GENRE));
        setCOMMENT(v23Tag.getFirst(FieldKey.COMMENT));
        setLANGUAGE(v23Tag.getFirst(FieldKey.LANGUAGE));
        setLYRICS(v23Tag.getFirst(FieldKey.LYRICS));
        List<Artwork> list = v23Tag.getArtworkList();
        if(list!=null && list.size()>0){
            setIMAGE(true);
        }else{
            setIMAGE(false);
        }
    }

    public CommonTag(String inputFilename, ID3v24Tag v24Tag) {
        filename = inputFilename;
        setALBUM(v24Tag.getFirst(FieldKey.ALBUM));
        setALBUMARTIST(v24Tag.getFirst(FieldKey.ALBUM_ARTIST));
        setTITLE(v24Tag.getFirst(FieldKey.TITLE));
        setARTIST(v24Tag.getFirst(FieldKey.ARTIST));
        setTRACKNUMBER(v24Tag.getFirst(FieldKey.TRACK));
        setTRACKTOTAL(v24Tag.getFirst(FieldKey.TRACK_TOTAL));
        setDISCNUMBER(v24Tag.getFirst(FieldKey.DISC_NO));
        setDISCTOTAL(v24Tag.getFirst(FieldKey.DISC_TOTAL));
        setDATE(v24Tag.getFirst(FieldKey.YEAR));
        setGENRE(v24Tag.getFirst(FieldKey.GENRE));
        setCOMMENT(v24Tag.getFirst(FieldKey.COMMENT));
        setLANGUAGE(v24Tag.getFirst(FieldKey.LANGUAGE));
        setLYRICS(v24Tag.getFirst(FieldKey.LYRICS));
        List<Artwork> list = v24Tag.getArtworkList();
        if(list!=null && list.size()>0){
            setIMAGE(true);
        }else{
            setIMAGE(false);
        }
    }

    public CommonTag(String inputFilename, WavTag wavTag) {
        filename = inputFilename;
//        setALBUM(v24Tag.getFirst(FieldKey.ALBUM));
//        setALBUMARTIST(v24Tag.getFirst(FieldKey.ALBUM_ARTIST));
//        setTITLE(v24Tag.getFirst(FieldKey.TITLE));
//        setARTIST(v24Tag.getFirst(FieldKey.ARTIST));
//        setTRACKNUMBER(v24Tag.getFirst(FieldKey.TRACK));
//        setTRACKTOTAL(v24Tag.getFirst(FieldKey.TRACK_TOTAL));
//        setDISCNUMBER(v24Tag.getFirst(FieldKey.DISC_NO));
//        setDISCTOTAL(v24Tag.getFirst(FieldKey.DISC_TOTAL));
//        setDATE(v24Tag.getFirst(FieldKey.YEAR));
//        setGENRE(v24Tag.getFirst(FieldKey.GENRE));
//        setCOMMENT(v24Tag.getFirst(FieldKey.COMMENT));
//        setLANGUAGE(v24Tag.getFirst(FieldKey.LANGUAGE));
//        setLYRICS(v24Tag.getFirst(FieldKey.LYRICS));
//        List<Artwork> list = v24Tag.getArtworkList();
//        if(list!=null && list.size()>0){
//            setIMAGE(true);
//        }else{
//            setIMAGE(false);
//        }
    }

}
