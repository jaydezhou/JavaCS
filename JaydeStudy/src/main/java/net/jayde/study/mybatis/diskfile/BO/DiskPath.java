package net.jayde.study.mybatis.diskfile.BO;

import lombok.Data;

@Data
public class DiskPath {
    String preLibraryId;
    String prePathId;
    String pathId;
    String pathFullText;
    String pathName;
    long selfPathCount;
    long selfFileCount;
    long selfSize;
    long sonPathCount;
    long sonFileCount;
    long sonSize;
    int level;
}
