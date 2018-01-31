package net.jayde.app.musicTag.util;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * cue bean
 * @author xuweilin
 *
 */
public class CueFileBean{
    private static Logger logger = LogManager.getLogger(CueFileBean.class.getName());
    private String performer; //performer
    private String albumName; //albumName
    private String fileName;  //fileName
    private List<CueSongBean> songs = null; //songs list
    public String getPerformer() {
        return performer;
    }
    public void setPerformer(String performer) {
        this.performer = performer;
    }
    public String getAlbumName() {
        return albumName;
    }
    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public List<CueSongBean> getSongs() {
        return songs;
    }
    public void setSongs(List<CueSongBean> songs) {
        this.songs = songs;
    }

    @Override
    public String toString() {
        return "CueFileBean{" +
                "performer='" + performer + '\'' +
                ", albumName='" + albumName + '\'' +
                ", fileName='" + fileName + '\'' +
                ", songs=" + songs +
                '}';
    }
}