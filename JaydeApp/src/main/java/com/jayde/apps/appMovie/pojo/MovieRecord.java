package com.jayde.apps.appMovie.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias(value="MovieRecord")
@Data
public class MovieRecord {
    private String id;
    private String name;
    private String movieId;
}
