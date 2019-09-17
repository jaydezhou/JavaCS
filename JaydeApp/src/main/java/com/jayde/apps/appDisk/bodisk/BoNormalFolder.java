package com.jayde.apps.appDisk.bodisk;

import java.io.File;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appDisk.bodisk
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-09-11 11:27
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-09-11 11:27
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class BoNormalFolder extends BoFolder {

    public BoNormalFolder(String inputFileName) {
        super(inputFileName);
    }

    public BoNormalFolder(File inputFile) {
        super(inputFile);
    }

}
