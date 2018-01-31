package net.jayde.app.music.pojo;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class MusicFile {

    private static Logger logger = LogManager.getLogger(MusicFile.class.getName());
    String id;
    String name;
    String msId;
    private MusicSong ms;

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

    public String getMsId() {
        return msId;
    }

    public void setMsId(String msId) {
        this.msId = msId;
    }

    public MusicSong getMs() {
        return ms;
    }

    public void setMs(MusicSong ms) {
        this.ms = ms;
    }
}
