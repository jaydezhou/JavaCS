package com.jayde.apps.appDisk.bolibrary;

/**
 * @ProjectName: JavaCS
 * @Package: com.jayde.apps.appDisk.bolibrary
 * @ClassName: ${TYPE_NAME}
 * @Description: java类作用描述
 * @Author: jayde
 * @CreateDate: 2019-08-13 15:27
 * @UpdateUser: The Modified user
 * @UpdateDate: 2019-08-13 15:27
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public interface AbstractFile {

    String getFileName();
    void setFileName();
    long getFileSize();
    void setFileSize();

    long getQuality();


}
