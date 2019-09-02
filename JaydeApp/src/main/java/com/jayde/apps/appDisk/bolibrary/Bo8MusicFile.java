package com.jayde.apps.appDisk.bolibrary;


import org.jaudiotagger.audio.AudioHeader;

import java.io.File;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appDisk.bolibrary
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-08-13 11:02
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-08-13 11:02
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class Bo8MusicFile extends BoFile {
    String id;
    String fileName;
    long fileSize;
    Bo7MusicCd parentCd = null;
    int mediaType = 0;
    BoMusicQuality quality;

    public Bo8MusicFile(File inputFile, Bo7MusicCd inputParent) {
        selfFile = inputFile;
        parentCd = inputParent;
    }

    @Override
    public float calculate() {
        return 0f;
    }

    @Override
    public float cycleCalculate() {
        calculate();
        return scoretotal;
    }

    @Override
    public void cycleShowTree() {

    }

    public static Bo8MusicFile cycleCreate() {
        return null;
    }
}
