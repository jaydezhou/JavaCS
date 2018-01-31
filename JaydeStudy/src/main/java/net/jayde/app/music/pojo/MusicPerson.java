package net.jayde.app.music.pojo;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class MusicPerson {

  private static Logger logger = LogManager.getLogger(MusicPerson.class.getName());
  public static int PERSON_FAVOURITE = 1;
  public static int PERSON_ALL = 0;
  String id;
  String name;
  String mgId;
  String m163Id;
  int favourite = 0;

  private MusicGroup mg;
  Set<MusicAlbum> albumSet = null;
  Set<MusicFavourite> favouriteSet = null;

  public MusicPerson() {}

  public MusicPerson(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public MusicPerson(String id, String name, String mgId, MusicGroup mg) {
    this.id = id;
    this.name = name;
    this.mgId = mgId;
    this.mg = mg;
  }

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

  public MusicGroup getMg() {
    return mg;
  }

  public void setMg(MusicGroup mg) {
    this.mg = mg;
  }

  public Set<MusicAlbum> getAlbumSet() {
    if (albumSet == null) albumSet = new HashSet<>();
    return albumSet;
  }

  public void setAlbumSet(Set<MusicAlbum> albumSet) {
    this.albumSet = albumSet;
  }

  public Set<MusicFavourite> getFavouriteSet() {
    if (favouriteSet == null) favouriteSet = new HashSet<>();
    return favouriteSet;
  }

  public void setFavouriteSet(Set<MusicFavourite> favouriteSet) {
    this.favouriteSet = favouriteSet;
  }

  public String getM163Id() {
    return m163Id;
  }

  public void setM163Id(String m163Id) {
    this.m163Id = m163Id;
  }

  public int getFavourite() {
    return favourite;
  }

  public void setFavourite(int favourite) {
    this.favourite = favourite;
  }

  @Override
  public String toString() {
    return  name ;
  }
}
