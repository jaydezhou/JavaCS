package net.jayde.app.music.dao;

import net.jayde.app.music.pojo.MusicGroup;
import net.jayde.app.music.pojo.MusicPerson;

import java.util.List;

public interface IMusicGroupDao {
    MusicGroup getRootMusicGroup();

    MusicGroup getMusicGroup(String id);

    List<MusicGroup> getSonMusicGroups(String preId);

    List<MusicPerson> getSonMusicPersons(String gId);


}
