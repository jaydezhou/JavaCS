package com.jayde.apps.appDisk.bodisk;

import org.dom4j.Element;
import org.jaudiotagger.audio.AudioHeader;

import java.io.File;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appDisk.bodisk
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-09-11 11:28
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-09-11 11:28
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class BoMusicSongFile extends BoSetFile {
    String encode = "";
    AudioHeader header;
    CommonTag tag;

    public BoMusicSongFile(String inputFileName) {
        super(inputFileName);
        initFileType();
    }

    public BoMusicSongFile(File inputFile) {
        super(inputFile);
        header = UtilBoMusic.readHeader(inputFile, suffix);
        tag = UtilBoMusic.readTag(inputFile, suffix);
        initFileType();
    }

    public BoMusicSongFile(Element inputEle) {
        super(inputEle);
        initFileType();
    }

    @Override
    public Element toElement(Element parentELement) {
        Element currentEle = parentELement.addElement("F");
        currentEle.addAttribute("name", filename);
        currentEle.addAttribute("size", String.valueOf(size));
        currentEle.addAttribute("modifyDate", String.valueOf(modifyDate));
        currentEle.addAttribute("attributes", attributes);
        currentEle.addAttribute("filetype", String.valueOf(fileType));
        currentEle.addAttribute("score", String.valueOf(score));
//        currentEle.addAttribute("suffix", suffix);
        currentEle.addAttribute("encode", encode);
        currentEle.addAttribute("id", id);
        currentEle.addAttribute("pid", pid);

        Element eleHeader = currentEle.addElement("HEADER");
        if (header != null) {
            eleHeader.addAttribute("read", "true");
            eleHeader.addAttribute("Encoding", header.getEncodingType());
            eleHeader.addAttribute("BitRate", header.getBitRate());
//                        eleHeader.addAttribute("BitRateNumber", header.getBitRateAsNumber() + "");
            eleHeader.addAttribute("SampleRate", header.getSampleRate());
//                        eleHeader.addAttribute("SampleRateNumber", header.getSampleRateAsNumber() + "");
            eleHeader.addAttribute("Format", header.getFormat());
            eleHeader.addAttribute("Channels", header.getChannels());
            eleHeader.addAttribute("VariableBitRate", header.isVariableBitRate() + "");
            eleHeader.addAttribute("TrackLength", header.getTrackLength() + "");
        } else {
            eleHeader.addAttribute("read", "false");
        }

        Element eleTag = currentEle.addElement("TAG");
        if (tag != null) {
            eleTag = tag.toElement(eleTag);
            eleTag.addAttribute("read", "true");
        } else {
            eleTag.addAttribute("read", "false");
        }

        return parentELement;
    }


    private void initFileType() {
        setFileType(AbstractBoFile.FILETYPE_FILE_BOMUSICSONGFILE);
    }

    @Override
    public String toString() {
        return "F(MS):" + filename;
    }
}
