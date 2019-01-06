package com.jayde.apps.appMovie.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias(value="MovieFile")
@Data
public class MovieFile {
    private String id;
    private String name;
    private String path;
}
