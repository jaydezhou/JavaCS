package com.jayde.apps.appMovie.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias(value="MovieActor")
@Data
public class MovieActor {
    private String id;
    private String chnName;
    private String engName;
}
