package com.jayde.apps.appDisk.bodisk;

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
    String encode;
    AudioHeader header;
    CommonTag tag;

    public BoMusicSongFile(String inputFileName) {
        super(inputFileName);
    }

    public BoMusicSongFile(File inputFile) {
        super(inputFile);
        header = UtilBoMusic.readHeader(inputFile, suffix);
    }

    @Override
    public String toString() {
        return "BoMusicSongFile(歌曲){" +
                "filename='" + filename + '\'' +
                '}';
    }
}
