package com.jayde.apps.appMovie.dao;

import org.mybatis.spring.SqlSessionTemplate;

public abstract class Dao {

    //注入以后就可以直接使用sqlsession
     SqlSessionTemplate sqlsession;

    public SqlSessionTemplate getSqlsession() {
        return sqlsession;
    }


    public void setSqlsession(SqlSessionTemplate sqlsession) {
        this.sqlsession = sqlsession;
    }



}
