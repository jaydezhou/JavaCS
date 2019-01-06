package com.jayde.apps.appMovie.pojo;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.util.UUID;

@Alias(value = "MovieGroup")
@Data
public class MovieGroup {
    private String id;
    private String chnName;
    private String engName;

    public MovieGroup() {
    setId(UUID.randomUUID().toString());
    }
}
