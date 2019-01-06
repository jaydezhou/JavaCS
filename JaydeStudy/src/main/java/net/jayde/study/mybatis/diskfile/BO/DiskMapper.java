package net.jayde.study.mybatis.diskfile.BO;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface DiskMapper {

    @Select("SELECT * FROM disklibrary WHERE libraryid = #{libraryId}")
    DiskLibrary getLibraryById(String libraryId);

    @Select("SELECT * FROM disklibrary WHERE libraryname = #{libraryName}")
    DiskLibrary getLibraryByName(String libraryName);

//    @Select("")
//    DiskLibrary listAllLibraries();

    @InsertProvider(type = SqlFactoryOfDisk.class, method = "insertDiskLibrary")
    boolean insertDiskLibrary(DiskLibrary diskLibrary);

    @Insert("INSERT INTO diskpath(prelibraryid,prepathid,pathid,pathfulltext,pathname,selfpathcount,selffilecount,selfsize,sonpathcount,sonfilecount,sonsize,level)" +
            " values(#{preLibraryId},#{prePathId},#{pathId},#{pathFullText},#{pathName},#{selfPathCount},#{selfFileCount},#{selfSize},#{sonPathCount},#{sonFileCount},#{sonSize},#{level})")
    boolean insertDiskFolder(DiskPath diskPath);

    @Select("SELECT * FROM diskpath WHERE pathid = #{pathId}")
    DiskPath getPathById(String pathId);

    @Select("SELECT * FROM diskpath WHERE pathname = #{pathName}")
    DiskPath getPathByName(String pathName);

    @Select("SELECT * FROM diskpath WHERE pathfulltext = #{pathFullText}")
    DiskPath getPathByFullText(String pathFullText);


    @Insert("INSERT INTO diskfile(prelibraryid,prepathid,fileid,filefulltext,filename,filesize,createdatetime,modifydatetime,attributes)" +
            " values(#{preLibraryId},#{prePathId},#{fileId},#{fileFullText},#{fileName},#{fileSize},#{createDatetime},#{modifyDatetime},#{attributes})")
    boolean insertDiskFile(DiskFile diskFile);

    @Select("SELECT * FROM diskfile WHERE fileid = #{fileId}")
    DiskFile getFileById(String fileId);

    @Select("SELECT * FROM diskpath where prelibraryid = #{preLibraryId}")
    List<DiskPath> getAllPathsByLibraryId(String libraryId);

    @Select("SELECT * FROM diskpath where prepathid = #{prePathId}")
    List<DiskPath> getAllPathsByPrepathId(String pathId);

    @Select("SELECT * FROM diskpath where prelibraryid=#{arg0} and level=#{arg1}")
    List<DiskPath> getAllPathsByLevel(String preLibraryId,int level);

    @Select("SELECT * FROM diskfile where prepathid = #{prePathId}")
    List<DiskFile> getAllFilesByPrepathId(String pathId);

    @Update("UPDATE disklibrary SET librarydiskname=#{libraryDiskName} " +
            "WHERE libraryid=#{libraryId}")
    boolean updateDiskLibraryInfo(DiskLibrary diskLibrary);

    @Update("UPDATE diskpath SET prepathid=#{prePathId},level=#{level}," +
            "selfpathcount=#{selfPathCount},selffilecount=#{selfFileCount},selfsize=#{selfSize}," +
            "sonpathcount=#{sonPathCount},sonfilecount=#{sonFileCount},sonsize=#{sonSize} " +
            "WHERE pathid=#{pathId}")
    boolean updateDiskPathInfo(DiskPath diskPath);

    @Select("select max(level) from diskpath where prelibraryid=#{preLibraryId}")
    int findMaxLevelByLibraryId(String preLibraryId);

    @Select("select min(level) from diskpath where prelibraryid=#{preLibraryId}")
    int findMinLevelByLibraryId(String preLibraryId);
}
