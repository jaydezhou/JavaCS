package net.jayde.app.music.util;


import java.sql.Connection;
import java.sql.DriverManager;

public class MusicDb {

  Connection conn = null;

  public void connDb() {
    try {
      MusicConfig mc = new MusicConfig();
      String[] connInfo = mc.getConnInfo();
      Class.forName(connInfo[1]);// Driver

      conn = DriverManager.getConnection(connInfo[2], connInfo[3], connInfo[4]);

    } catch (Exception sqle) {
      System.out.println(sqle.getMessage());
    }
  }

  public void disconnDb() {
    try {
      conn.close();
    } catch (Exception sqle) {
      System.out.println(sqle.getMessage());
    }
  }
}
