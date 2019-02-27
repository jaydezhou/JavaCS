package com.jayde.apps.appMovie.dao;

import com.jayde.apps.appMovie.pojo.MovieInfo;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

public class MovieDao extends Dao {

    //注入以后就可以直接使用sqlsession
//    private SqlSessionTemplate sqlsession;
//
//    public void setSqlsession(SqlSessionTemplate sqlsession) {
//        this.sqlsession = sqlsession;
//    }

    //用sqlsession去操作数据库
    public void insert(Map user) {
        sqlsession.insert("UserMapper.insert", user);
    }

    public void findById(String id) {
        sqlsession.selectOne("MovieMapper.selectGroupById", id);
        System.out.println(sqlsession.selectOne("MovieMapper.selectGroupById", id).toString());
        //因为输出值是一个map集合，所以打印一个map
    }

    public List<MovieInfo> findAllInfo() {
      return   sqlsession.selectList("MovieMapper.selectAllInfo");
//        System.out.println(sqlsession.selectList("MovieMapper.selectAllInfo"));
        //因为输出值是一个map集合，所以打印一个map
    }

    public static void main(String[] args) {

        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("mybatis/Spring-mybatis.xml");
//        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("mybatis/Spring-mybatis.xml");
        MovieDao movieDao= (MovieDao) ctx.getBean("MovieDao");
        //固定格式  取出bean中创建的UserMapper对象
        List<MovieInfo> listInfos = movieDao.findAllInfo();
        System.out.println(listInfos.size());
        for(MovieInfo movieInfo:listInfos){
            System.out.println(movieInfo);
        }
        movieDao.findById("1");
    }


//    private MovieMapper movieMapper = getSqlSession().getMapper(MovieMapper.class);
//
//    public MovieDao() {
//    }
//
//    public static void main(String[] args) {
//        MovieDao movieDao = new MovieDao();
//        movieDao.addMovieGroup(null);
//    }
//
//    public MovieGroup addMovieGroup(MovieGroup movieGroup) {
//        if (movieGroup == null) {
//            movieGroup = new MovieGroup();
//            movieGroup.setChnName("未命名(" + new Date() + ")");
//            movieGroup.setEngName("NoName(" + new Date() + ")");
//        }
//        while (movieMapper.selectGroupById(movieGroup.getId()) != null) {
//            movieGroup.setId(UUID.randomUUID().toString());
//        }
//        movieMapper.insertGroup(movieGroup);
//        commit();
//        return movieGroup;
//    }
}
