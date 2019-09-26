package com.jayde.apps.appDisk.bodisk;

import org.dom4j.Element;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
public class BoSetFolder extends BoFolder {
    int level;
    String typeName;

    public BoSetFolder(String inputFileName) {
        super(inputFileName);
        initFileType();
    }

    public BoSetFolder(File inputFile) {
        super(inputFile);
        initFileType();
    }
    public BoSetFolder(Element inputEle) {
        super(inputEle);
        initFileType();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    private void initFileType() {
        setFileType(AbstractBoFile.FILETYPE_FOLDER_BOSETFOLDER);
    }


    public static boolean isLibraryFolder(BoFolder boFolder) {
        String filename = boFolder.getFilename();
        Matcher matcher = pattern.matcher(filename);
        if(matcher.find()){
            System.out.println("OK");
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "BSF:" +filename;
    }

    static Pattern pattern = Pattern.compile("标准文件夹");
}
