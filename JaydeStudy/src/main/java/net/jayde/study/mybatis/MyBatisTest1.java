package net.jayde.study.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisTest1 {
    SqlSessionFactory sqlSessionFactory;

    private void beginConn() {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory =
                    new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void endConn() {
    }

    public MyBatisTest1() {
        beginConn();
    }

    public static void main(String[] args) {
        MyBatisTest1 test1 = new MyBatisTest1();
    }
}
