package com.jayde.apps.appDisk.bodisk;

import org.dom4j.Element;

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
public class BoMusicOtherFile extends BoSetFile {

    public BoMusicOtherFile(String inputFileName) {
        super(inputFileName);
        initFileType();
    }

    public BoMusicOtherFile(File inputFile) {
        super(inputFile);
        initFileType();
    }

    public BoMusicOtherFile(Element inputEle) {
        super(inputEle);
        initFileType();
    }

    private void initFileType() {
        setFileType(AbstractBoFile.FILETYPE_FILE_BOMUSICOTHERFILE);
    }

    @Override
    public String toString() {
        return "F(MO):" + filename;
    }
}
