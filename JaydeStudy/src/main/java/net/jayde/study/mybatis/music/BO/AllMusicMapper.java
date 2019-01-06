package net.jayde.study.mybatis.music.BO;

import java.util.List;

public interface AllMusicMapper {
    Singer getSingerById(String singerid);
    Singer getSingerByName(String singername);
    List<Song> getSongsBySingerId(String singerid);
    Song getSongById(String songid);
}
