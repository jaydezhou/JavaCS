package net.jayde.app.music.util;

import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import net.jayde.app.music.pojo.MusicFavourite;
import net.jayde.app.music.pojo.MusicGroup;
import net.jayde.app.music.pojo.MusicLibrary;
import net.jayde.app.music.pojo.MusicPerson;

public class MusicReadDb extends MusicDb {

  MusicLibrary library = new MusicLibrary();
  private static Logger logger = LogManager.getLogger(MusicReadDb.class.getName());



  public void cycleGroup(MusicGroup preGroup, int personFavourite) {
    try {
      Statement stmt = conn.createStatement();

      ResultSet rsGroup =
          stmt.executeQuery("select * from musicgroup where preId='" + preGroup.getId() + "' order by id");
      while (rsGroup.next()) {
        String gid = rsGroup.getString("id");
        String gname = rsGroup.getString("name");
        MusicGroup mg = new MusicGroup(gid, gname);
        System.out.println(mg);
        cyclePerson(mg, gid, personFavourite);

        cycleGroup(mg, personFavourite);
        if (preGroup.getId().equals("0")) {
          library.getGroups().add(mg);
        } else {
          preGroup.getSonGroups().add(mg);
        }
      }
      rsGroup.close();
      stmt.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }


  public void cyclePerson(MusicGroup group, String gid, int personFavourite) {
    try {
      Statement stmt = conn.createStatement();
      ResultSet rsPerson = stmt.executeQuery("select * from musicPerson where mgid='" + gid + "' order by id");
      while (rsPerson.next()) {

        String pid = rsPerson.getString("id");
        String pname = rsPerson.getString("name");
        MusicPerson person = new MusicPerson(pid, pname, gid, group);
        person.setM163Id(rsPerson.getString("m163id").trim());
        person.setFavourite(rsPerson.getInt("favourite"));
        // cycleQuestions(project,pid);
        if (personFavourite == MusicPerson.PERSON_ALL || person.getFavourite() == MusicPerson.PERSON_FAVOURITE) {
          // if (person.getFavourite() == MusicPerson.PERSON_FAVOURITE) {
          // System.out.println(person.getFavourite());
          // }
          Statement stmtFavourite = conn.createStatement();
          ResultSet rsFavourite =
              stmtFavourite.executeQuery("select * from musicfavourite where mpid='" + pid + "' order by name");
          while (rsFavourite.next()) {
            MusicFavourite mf = new MusicFavourite();
            mf.setMgId(gid);
            mf.setMpid(pid);
            mf.setId(rsFavourite.getString("id"));
            mf.setName(rsFavourite.getString("name"));
            mf.setM163Id(rsFavourite.getString("m163id"));
            person.getFavouriteSet().add(mf);
          }
          group.getSonPersons().add(person);
          Statement stmtf = conn.createStatement();
          ResultSet rsf = stmtf.executeQuery("select count(id) from musicFavourite where mpid='" + pid + "';");
          if (rsf.next()) {
            if (rsf.getInt(1) == 0) {
              // System.out.println(person);
            }
          }
        }
      }
      rsPerson.close();
      stmt.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }


  public MusicLibrary readByDb() {
    connDb();
    MusicGroup rootGroup = new MusicGroup("0", "root");
    cycleGroup(rootGroup, MusicPerson.PERSON_FAVOURITE);
    disconnDb();
    return library;
  }

  public static void main(String[] args) {
    MusicReadDb musicReadDb = new MusicReadDb();
    System.out.println(musicReadDb.readByDb().toString());
  }

}
