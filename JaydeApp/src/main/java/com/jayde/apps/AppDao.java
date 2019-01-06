package com.jayde.apps;

import org.apache.ibatis.session.SqlSession;

public abstract class AppDao {

    SqlSession sqlSession = AppMybatisSession.getInstance().getSqlSession();

    public AppDao() {
    }

    public void commit() {
        sqlSession.commit();
    }

    public SqlSession getSqlSession() {
        return sqlSession;
    }
}
