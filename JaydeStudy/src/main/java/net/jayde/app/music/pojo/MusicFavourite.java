package net.jayde.app.music.pojo;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class MusicFavourite {

    private static Logger logger = LogManager.getLogger(MusicFavourite.class.getName());
    String id;
    String name;
    String mgId;
    String mpid;
    private MusicGroup mg;
    private MusicPerson mp;
    String m163Id;
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

    public String getMpid() {
        return mpid;
    }

    public void setMpid(String mpid) {
        this.mpid = mpid;
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

    public String getM163Id() {
        return m163Id;
    }

    public void setM163Id(String m163Id) {
        this.m163Id = m163Id;
    }

    @Override
    public String toString() {
        return name;
    }
}
