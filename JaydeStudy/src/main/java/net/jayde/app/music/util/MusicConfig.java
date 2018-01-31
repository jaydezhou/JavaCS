package net.jayde.app.music.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.commons.configuration2.XMLConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class MusicConfig extends XMLConfiguration {

  private static Logger logger = LogManager.getLogger(MusicConfig.class.getName());

  public String[] getConnInfo() {
    String connType = "";
    String connDriver = "";
    String connUrl = "";
    String connLogin = "";
    String connPass = "";
    try {
      String resource = "music-config.xml";
      Configurations configs = new Configurations();
      try {
        XMLConfiguration config = configs.xml(resource);
        String usedatabase = config.getString("usedatabase");
        String key = "databases.database.name";
        int keyok = -1;
        for (String name : config.getStringArray(key)) {
          keyok++;
          if (name.equalsIgnoreCase(usedatabase)) {
            key = "databases.database(" + keyok + ")";
            connType = config.getString(key + ".type");
            connDriver = config.getString(key + ".driver");
            connUrl = config.getString(key + ".url");
            connLogin = config.getString(key + ".login");
            connPass = config.getString(key + ".password");
//            connUrl = connUrl + "?user=" + connLogin + "&password=" + connPass;
          }
        }
        // access configuration properties
      } catch (ConfigurationException cex) {
        // Something went wrong
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    return new String[] {connType, connDriver, connUrl,connLogin,connPass};
  }


  public static void main(String[] args) {
    MusicConfig mc = new MusicConfig();
    String[] connInfo = mc.getConnInfo();
    System.out.println(connInfo[0]);
    System.out.println(connInfo[1]);
    System.out.println(connInfo[2]);
    try {
      Class.forName(connInfo[1]);
      Connection connection = DriverManager.getConnection(connInfo[2],connInfo[3],connInfo[4]);
      Statement stmt = connection.createStatement();
      ResultSet rs = stmt.executeQuery("select * from musicgroup order by id");
      while(rs.next()){
        System.out.println(rs.getString("name"));
      }
      rs.close();
      stmt.close();
      connection.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
