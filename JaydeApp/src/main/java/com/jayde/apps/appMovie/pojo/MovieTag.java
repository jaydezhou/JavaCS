package com.jayde.apps.appMovie.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias(value="MovieTag")
@Data
public class MovieTag {
    private String id;
    private String chnName;
    private String engName;
}
