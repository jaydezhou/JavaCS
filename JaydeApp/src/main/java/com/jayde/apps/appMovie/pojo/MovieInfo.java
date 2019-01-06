package com.jayde.apps.appMovie.pojo;

import com.jayde.mybatis.util.ID;
import com.jayde.mybatis.util.TableName;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias(value="MovieInfo")
@Data
@TableName(name = "movieinfo")
public class MovieInfo {
    private String movieId;
    private String cName;
    private String eName;


}
