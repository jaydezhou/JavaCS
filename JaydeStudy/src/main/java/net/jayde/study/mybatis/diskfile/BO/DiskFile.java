package net.jayde.study.mybatis.diskfile.BO;

import lombok.Data;

@Data
public class DiskFile {
    String preLibraryId;
    String prePathId;
    String fileId;
    String fileFullText;
    String fileName;
    long fileSize;
    String createDatetime;
    String modifyDatetime;
    String attributes;
}
