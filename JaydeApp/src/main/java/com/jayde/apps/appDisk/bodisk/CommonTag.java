package com.jayde.apps.appDisk.bodisk;

import org.jaudiotagger.audio.wav.WavTag;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.datatype.Artwork;
import org.jaudiotagger.tag.flac.FlacTag;
import org.jaudiotagger.tag.id3.*;
import org.jaudiotagger.tag.vorbiscomment.VorbisCommentFieldKey;
import org.jaudiotagger.tag.vorbiscomment.VorbisCommentTag;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
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

    int loadErrorCode = 0;
    String loadErrorMessage = "";

    public static int LOAD_ERROR_CODE_1 = 1;
    public static String LOAD_ERROR_MESSAGE_1 = "字符串不符合xml格式";

    String filename;

    public int getLoadErrorCode() {
        return loadErrorCode;
    }

    public void setLoadErrorCode(int loadErrorCode) {
        this.loadErrorCode = loadErrorCode;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public String getALBUM() {
        return ALBUM;
    }

    public void setALBUM(String inputAlbum) {
        if (inputAlbum == null) {
            ALBUM = "";
        } else {
            ALBUM = inputAlbum.trim();
            if (cheXmlString(ALBUM) == false) {
                setLoadErrorCode(LOAD_ERROR_CODE_1);
                loadErrorMessage = "ALBUM:乱码" ;
            }
        }
    }

    public String getALBUMARTIST() {
        return ALBUMARTIST;
    }

    public void setALBUMARTIST(String inputAlbumArtist) {
        if (inputAlbumArtist == null) {
            ALBUMARTIST = "";
        } else {
            ALBUMARTIST = inputAlbumArtist.trim();
            if (cheXmlString(ALBUMARTIST) == false) {
                setLoadErrorCode(LOAD_ERROR_CODE_1);
                loadErrorMessage += "  ALBUMARTIST:乱码" ;
            }
        }
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String inputTitle) {
        if (inputTitle == null) {
            TITLE = "";
        } else {
            TITLE = inputTitle.trim();
            if (cheXmlString(TITLE) == false) {
                setLoadErrorCode(LOAD_ERROR_CODE_1);
                loadErrorMessage += "  TITLE:乱码" ;
            }
        }
    }

    public String getARTIST() {
        return ARTIST;
    }

    public void setARTIST(String inputArtist) {
        if (inputArtist == null) {
            ARTIST = "";
        } else {
            ARTIST = inputArtist.trim();
            if (cheXmlString(ARTIST) == false) {
                setLoadErrorCode(LOAD_ERROR_CODE_1);
                loadErrorMessage += "  ARTIST:乱码" ;
            }
        }
    }

    public String getTRACKNUMBER() {
        return TRACKNUMBER;
    }

    public void setTRACKNUMBER(String inputTrackNumber) {
        if (inputTrackNumber == null) {
            TRACKNUMBER = "";
        } else {
            TRACKNUMBER = inputTrackNumber.trim();
            if (cheXmlString(TRACKNUMBER) == false) {
                setLoadErrorCode(LOAD_ERROR_CODE_1);
                loadErrorMessage += "  TRACKNUMBER:乱码" ;
            }
        }
    }

    public String getTRACKTOTAL() {
        return TRACKTOTAL;
    }

    public void setTRACKTOTAL(String inputTrackTotal) {
        if (inputTrackTotal == null) {
            TRACKTOTAL = "";
        } else {
            TRACKTOTAL = inputTrackTotal.trim();
            if (cheXmlString(TRACKTOTAL) == false) {
                setLoadErrorCode(LOAD_ERROR_CODE_1);
                loadErrorMessage += "  TRACKTOTAL:乱码" ;
            }
        }
    }

    public String getDISCNUMBER() {
        return DISCNUMBER;
    }

    public void setDISCNUMBER(String inputDiscNumber) {
        if (inputDiscNumber == null) {
            DISCNUMBER = "";
        } else {
            DISCNUMBER = inputDiscNumber.trim();
            if (cheXmlString(DISCNUMBER) == false) {
                setLoadErrorCode(LOAD_ERROR_CODE_1);
                loadErrorMessage += "  DISCNUMBER:乱码" ;
            }
        }
    }

    public String getDISCTOTAL() {
        return DISCTOTAL;
    }

    public void setDISCTOTAL(String inputDiscTotal) {
        if (inputDiscTotal == null) {
            DISCTOTAL = "";
        } else {
            DISCTOTAL = inputDiscTotal.trim();
            if (cheXmlString(DISCTOTAL) == false) {
                setLoadErrorCode(LOAD_ERROR_CODE_1);
                loadErrorMessage += "  DISCTOTAL:乱码" ;

            }
        }
    }

    public String getDATE() {
        return DATE;
    }

    public void setDATE(String inputDate) {
        if (inputDate == null) {
            DATE = "";
        } else {
            DATE = inputDate.trim();
            if (cheXmlString(DATE) == false) {
                setLoadErrorCode(LOAD_ERROR_CODE_1);
                loadErrorMessage += "  DATE:乱码" ;
            }
        }
    }

    public String getGENRE() {
        return GENRE;
    }

    public void setGENRE(String inputGenre) {
        if (inputGenre == null) {
            GENRE = "";
        } else {
            GENRE = inputGenre.trim();
            if (cheXmlString(GENRE) == false) {
                setLoadErrorCode(LOAD_ERROR_CODE_1);
                loadErrorMessage += "  GENRE:乱码" ;
            }
        }
    }

    public String getCOMMENT() {
        return COMMENT;
    }

    public void setCOMMENT(String inputComment) {
        if (inputComment == null) {
            COMMENT = "";
        } else {
            COMMENT = inputComment.trim();
            if (cheXmlString(COMMENT) == false) {
                setLoadErrorCode(LOAD_ERROR_CODE_1);
                loadErrorMessage += "  COMMENT:乱码" ;
            }
        }
    }

    public String getLYRICS() {
        return LYRICS;
    }

    public void setLYRICS(String inputLyrics) {
        if (inputLyrics == null) {
            LYRICS = "";
        } else {
            LYRICS = inputLyrics.trim();
            if (cheXmlString(LYRICS) == false) {
                setLoadErrorCode(LOAD_ERROR_CODE_1);
                loadErrorMessage += "  LYRICS:乱码" ;
            }
        }
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

    public void setLANGUAGE(String inputLanguge) {
        if (inputLanguge == null) {
            LANGUAGE = "";
        } else {
            LANGUAGE = inputLanguge.trim();
            if (cheXmlString(LANGUAGE) == false) {
                setLoadErrorCode(LOAD_ERROR_CODE_1);
                loadErrorMessage += "  LANGUAGE:乱码" ;
            }
        }
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getLoadErrorMessage() {
        return loadErrorMessage;
    }

    public void setLoadErrorMessage(String loadErrorMessage) {
        this.loadErrorMessage = loadErrorMessage;
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
        if (list != null && list.size() > 0) {
            setIMAGE(true);
        } else {
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
        if (list != null && list.size() > 0) {
            setIMAGE(true);
        } else {
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
        if (list != null && list.size() > 0) {
            setIMAGE(true);
        } else {
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
        if (list != null && list.size() > 0) {
            setIMAGE(true);
        } else {
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

    private static boolean cheXmlString(String str) {
        if (str.length() == 0) {
            return true;
        }
        str = "<ROOT><Node att=\""+str+"\"/></ROOT>";
        System.out.println("------------------");
        boolean flag = true;
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new InputSource(new StringReader(str)));
            System.out.println(document);
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            flag = false;
        }
        return flag;
//        Document document = DocumentHelper.createDocument();
//        Element rootEle = document.addElement("root");
//        rootEle.addAttribute("att", str);
//        System.out.println(str);
//        System.out.println(document.supportsParent());
//        return document.supportsParent();
//        str = "<att name=\"" + str + "\"/>";
//        return
    }
}
