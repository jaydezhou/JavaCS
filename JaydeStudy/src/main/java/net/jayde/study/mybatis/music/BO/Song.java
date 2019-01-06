package net.jayde.study.mybatis.music.BO;

import lombok.Data;

@Data
public class Song {
    String songId;
    String singerId;
    String cnName;
    String foreignName;
}
