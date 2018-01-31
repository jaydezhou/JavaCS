package net.jayde.app.music.pojo;

import java.util.LinkedList;
import java.util.List;

import net.jayde.app.music.dao.MusicDao;
import net.jayde.app.music.dao.mybatisDao.MusicDaoMybatis;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class MusicLibrary {

  private static Logger logger = LogManager.getLogger(MusicLibrary.class.getName());
  List<MusicGroup> groups = null;

  public List<MusicGroup> getGroups() {
    if(groups==null){
      groups = new LinkedList<>();
    }
    return groups;
  }

  public void setGroups(List<MusicGroup> groups) {
    this.groups = groups;
  }

}
