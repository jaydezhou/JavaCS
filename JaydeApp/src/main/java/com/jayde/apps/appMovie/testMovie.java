package com.jayde.apps.appMovie;

import com.jayde.apps.appMovie.dao.MovieMapper;
import com.jayde.apps.appMovie.pojo.MovieGroup;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class testMovie {
    public static void main(String[] args) {
        String resource = "mybatis/mybatis-mysql-apps.xml";
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader(resource);
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            MovieMapper movieMapper = sqlSession.getMapper(MovieMapper.class);
            MovieGroup group = movieMapper.selectGroupById("1");
            System.out.println(group);
            MovieGroup group1 = new MovieGroup();
            group1.setChnName("新增加");
            group1.setEngName("newGroup");
            movieMapper.insertGroup(group1);
            sqlSession.commit();
            sqlSession.close();
        } finally {
            sqlSession.close();
        }

    }
}
