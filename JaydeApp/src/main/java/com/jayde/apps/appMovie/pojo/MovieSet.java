package com.jayde.apps.appMovie.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias(value="MovieSet")
@Data
public class MovieSet {
    private String id;
    private String chnName;
    private String engName;
}
