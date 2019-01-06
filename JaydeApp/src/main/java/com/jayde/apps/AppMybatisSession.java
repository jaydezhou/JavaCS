package com.jayde.apps;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class AppMybatisSession {
    private static AppMybatisSession ourInstance = new AppMybatisSession();

    public static AppMybatisSession getInstance() {
        return ourInstance;
    }

    private static SqlSessionFactory sqlSessionFactory;
    private static SqlSession sqlSession;

    public  SqlSession getSqlSession() {
        return sqlSession;
    }


    private AppMybatisSession() {
        String resource = "mybatis/mybatis-mysql-apps.xml";
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader(resource);
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        sqlSession = sqlSessionFactory.openSession();
    }


}
