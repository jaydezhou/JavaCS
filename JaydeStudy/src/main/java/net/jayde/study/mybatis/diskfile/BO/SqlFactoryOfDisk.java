package net.jayde.study.mybatis.diskfile.BO;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

public class SqlFactoryOfDisk {

    public String insertDiskLibrary(final DiskLibrary diskLibrary) {
        return new SQL() {
            {
                INSERT_INTO("disklibrary");
                if (diskLibrary.getLibraryId() != null) {
                    VALUES("libraryid", "#{libraryId}");
                }
                if (diskLibrary.getLibraryName() != null) {
                    VALUES("libraryname", "#{libraryName}");
                }
                if (diskLibrary.getLibraryDiskName() != null) {
                    VALUES("librarydiskname", "#{librarydiskname}");
                }
                if (diskLibrary.getLibraryRootPath() != null) {
                    VALUES("libraryrootpath", "#{libraryRootPath}");
                }
                VALUES("librarypathcount", "#{libraryPathCount}");
                VALUES("libraryfilecount", "#{libraryFileCount}");
                VALUES("librarysize", "#{librarySize}");
            }
        }.toString();

//        DiskLibrary library = (DiskLibrary) para.get("bean");
//
//        SQL sql = new SQL();
//
//        sql.INSERT_INTO("disklibrary");
//
//        if (library.getLibraryId() != null) {
//            sql.VALUES("libraryid", library.getLibraryId());
//        }
//
//        if(library.getLibraryName() != null){
//            String libraryName = library.getLibraryName();
//            sql.VALUES("libraryname","#{libraryName}");
//        }
//
//        if(library.getLibraryDiskName() != null){
//            sql.VALUES("librarydiskname",library.getLibraryDiskName());
//        }
//
//        if(library.getLibraryRootPath() != null){
//            sql.VALUES("libraryrootpath",library.getLibraryRootPath());
//        }
//
//        System.out.println(sql.toString());
//        return sql.toString();
    }
}
