package net.jayde.app.music.pojo;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class MusicCd {

    private static Logger logger = LogManager.getLogger(MusicCd.class.getName());

    String id;
    String name;
    String mgId;
    String mpId;
    String maId;
    private MusicGroup mg;
    private MusicPerson mp;
    private MusicAlbum ma;
    Set<MusicSong> songSet =null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMgId() {
        return mgId;
    }

    public void setMgId(String mgId) {
        this.mgId = mgId;
    }

    public String getMpId() {
        return mpId;
    }

    public void setMpId(String mpId) {
        this.mpId = mpId;
    }

    public String getMaId() {
        return maId;
    }

    public void setMaId(String maId) {
        this.maId = maId;
    }

    public MusicGroup getMg() {
        return mg;
    }

    public void setMg(MusicGroup mg) {
        this.mg = mg;
    }

    public MusicPerson getMp() {
        return mp;
    }

    public void setMp(MusicPerson mp) {
        this.mp = mp;
    }

    public MusicAlbum getMa() {
        return ma;
    }

    public void setMa(MusicAlbum ma) {
        this.ma = ma;
    }

    public Set<MusicSong> getSongSet() {
        if(songSet == null)
            songSet = new HashSet<>();
        return songSet;
    }

    public void setSongSet(Set<MusicSong> songSet) {
        this.songSet = songSet;
    }
}
