package net.jayde.app.music.pojo;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class MusicGroup {

  private static Logger logger = LogManager.getLogger(MusicGroup.class.getName());

  String id;
  String name;
  String m163Id;
  List<MusicPerson> sonPersons = null;
  List<MusicGroup> sonGroups = null;

  public MusicGroup() {

  }

  public MusicGroup(String inputId, String inputName) {
    id = inputId;
    name = inputName;
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

  public List<MusicPerson> getSonPersons() {
    if (sonPersons == null) {
      sonPersons = new LinkedList<>();
    }
    return sonPersons;
  }

  public void setSonPersons(List<MusicPerson> sonPersons) {
    this.sonPersons = sonPersons;
  }

  public List<MusicGroup> getSonGroups() {
    if (sonGroups == null) {
      sonGroups = new LinkedList<>();
    }
    return sonGroups;
  }

  public void setSonGroups(List<MusicGroup> sonGroups) {
    this.sonGroups = sonGroups;
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
