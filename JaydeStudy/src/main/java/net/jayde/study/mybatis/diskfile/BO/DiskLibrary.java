package net.jayde.study.mybatis.diskfile.BO;

import lombok.Data;

@Data
public class DiskLibrary {
    String libraryId;
    String libraryName;
    String libraryDiskName;
    String libraryRootPath;
    long libraryPathCount;
    long libraryFileCount;
    long librarySize;

    @Override
    public String toString() {
        return "DiskLibrary{" +
                "libraryId='" + libraryId + '\'' +
                ", libraryName='" + libraryName + '\'' +
                '}';
    }
}
