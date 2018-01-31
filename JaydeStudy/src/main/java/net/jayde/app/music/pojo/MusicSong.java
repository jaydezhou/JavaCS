package net.jayde.app.music.pojo;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class MusicSong {

  private static Logger logger = LogManager.getLogger(MusicSong.class.getName());

  String id;
  String name;
  String mgId;
  String mpId;
  String maId;
  String mcId;
  private MusicGroup mg;
  private MusicPerson mp;
  private MusicAlbum ma;
  private MusicCd mc;
  Set<MusicFile> fileSet = null;

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

  public String getMcId() {
    return mcId;
  }

  public void setMcId(String mcId) {
    this.mcId = mcId;
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

  public MusicCd getMc() {
    return mc;
  }

  public void setMc(MusicCd mc) {
    this.mc = mc;
  }

  public Set<MusicFile> getFileSet() {
    if (fileSet == null) fileSet = new HashSet<>();
    return fileSet;
  }

  public void setFileSet(Set<MusicFile> fileSet) {
    this.fileSet = fileSet;
  }
}
