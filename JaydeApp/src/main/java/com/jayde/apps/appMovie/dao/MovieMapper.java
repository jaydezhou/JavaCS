package com.jayde.apps.appMovie.dao;

import com.jayde.apps.appMovie.pojo.MovieGroup;
import com.jayde.apps.appMovie.pojo.MovieInfo;

import java.util.List;

public interface MovieMapper {

    //操作MovieGroup对象
    boolean insertGroup(MovieGroup group);
    boolean deleteGroup(MovieGroup group);
    boolean updateGroup(MovieGroup group);
    int countGroup(MovieGroup group);
    MovieGroup selectGroupById(String inputId);
    List<MovieGroup> selectSonGroups(String inputId);

    MovieInfo selectInfoById(String inputId);
    List<MovieInfo> selectAllInfo();

    //操作MovieInfo对象


    //操作MovieTag对象


    //操作MovieFile对象


    //操作MovieRecord对象


    //操作MovieSet对象


    //操作MovieImage对象


    //操作MovieActor对象


}
