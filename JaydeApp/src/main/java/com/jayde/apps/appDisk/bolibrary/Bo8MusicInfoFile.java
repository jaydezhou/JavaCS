package com.jayde.apps.appDisk.bolibrary;

import java.io.File;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appDisk.bolibrary
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-08-28 11:40
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-08-28 11:40
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class Bo8MusicInfoFile extends BoFile {
    Bo7MusicCd parentCd = null;

    public Bo8MusicInfoFile(File inputFile, Bo7MusicCd inputParent) {
        selfFile = inputFile;
        parentCd = inputParent;
    }

    @Override
    public void calculate() {

    }

    @Override
    public void cycleCalculate() {

    }

    @Override
    public Bo8MusicInfoFile cycleCreate() {
        return null;
    }
}
