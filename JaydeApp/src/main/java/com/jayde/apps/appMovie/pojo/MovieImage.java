package com.jayde.apps.appMovie.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias(value="MovieImage")
@Data
public class MovieImage {
    private String id;
    private String name;
}
