package net.jayde.app.pm.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ImportMdToDb {

  private static Logger logger = LogManager.getLogger(ImportMdToDb.class.getName());
  String[] fieldCnNames = {};
  String[] filedEnNames = {};
  String mdText = "";


  String sourceURL = "jdbc:h2:d:\\IdeaProjects\\JaydeCS\\db\\Db_h2_mybatis.mv.db";
  Connection conn = null;

  public void connDb() {
    try {
      String user = "";
      String key = "";
      Class.forName("org.h2.Driver");// HSQLDB Driver

      conn = DriverManager.getConnection(sourceURL, "", "");

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

  public void importMd() {

    String pid = "";
    String[] texts = mdText.split("\n");
    for (int i = 0; i < texts.length; i++) {
      String text = texts[i];
      System.out.println("*****:" + text);
      String fields[] = text.split("\\|");
      String fieldName = fields[1].trim();
      String fieldValue = fields[2].trim();
      if(fieldName.equals("")){
        System.out.println("continue"+fieldValue);
        continue;
      }
      if (fieldName.equals("pname")) {
        try {
          Statement stmt = conn.createStatement();
          String selectSQL = "select * from p2project where pname = '" + fieldValue + "'";
          System.out.println(selectSQL);
          ResultSet rs = stmt.executeQuery(selectSQL);
          if (rs.next()) {
            pid = rs.getString("id");
          }
          System.out.println(pid);
          rs.close();
          stmt.close();
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }
      } else {
        try {
          Statement stmt = conn.createStatement();
          String updateSql = "update p2project set " +fieldName+ "='" + fieldValue + "' where id='" + pid + "';";
          System.out.println(updateSql);
          stmt.executeUpdate(updateSql);
          conn.commit();
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }
      }
    }
  }

  public ImportMdToDb() {
    connDb();
    importMd();
    disconnDb();
  }

  public static void main(String[] args) {
    ImportMdToDb importMdToDb = new ImportMdToDb();

  }
}
