package com.jayde.apps.appDisk.bodisk;

import java.io.File;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appDisk.bodisk
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-09-11 11:25
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-09-11 11:25
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class BoSetFile extends BoFile {

    public static int SETFILE_TYPE_MUSIC = 11;
    public static int SETFILE_TYPE_MUSICINFO = 12;
    public static int SETFILE_TYPE_MUSICOTHER = 13;

    public BoSetFile(String inputFileName) {
        super(inputFileName);
    }

    public BoSetFile(File inputFile) {
        super(inputFile);
    }

}
