package net.jayde.app.pm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import net.jayde.app.pm.pojo.ProjectGroup;
import net.jayde.app.pm.pojo.ProjectLibrary;
import net.jayde.app.pm.pojo.ProjectObject;
import net.jayde.app.pm.pojo.ProjectQuestion;

public class PmReadDb {

  // String sourceURL =
  // "jdbc:h2:c:\\Users\\Administrator\\IdeaProjects\\JaydeCS\\db\\Db_h2_mybatis.mv.db";
  String sourceURL = "jdbc:h2:d:\\IdeaProjects\\JaydeCS\\db\\Db_h2_mybatis.mv.db";
  Connection conn = null;
  ProjectLibrary library = new ProjectLibrary();

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


  public void cycleGroup() {
    try {
      Statement stmt = conn.createStatement();
      ResultSet rsGroup = stmt.executeQuery("select * from p2group order by sortorder");
      while (rsGroup.next()) {
        String gid = rsGroup.getString("id");
        String gname = rsGroup.getString("gname");
        ProjectGroup group = new ProjectGroup();
        group.setName(gname);
        System.out.println(gid + ":" + gname);
        cycleProject(group, gid);
        library.getGroups().add(group);
      }
      rsGroup.close();
      stmt.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }


  public void cycleProject(ProjectGroup group, String gid) {
    try {
      Statement stmt = conn.createStatement();
      ResultSet rsProject = stmt.executeQuery("select * from p2Project where gid='" + gid + "' order by id");
      while (rsProject.next()) {
        String pid = rsProject.getString("id");
        String pname = rsProject.getString("pname");
        ProjectObject project = new ProjectObject();
        project.setPname(pname);
        project.setId(pid);
        project.setGid(gid);
        project.setSortorder(rsProject.getString("SORTORDER"));
        project.setPowner(rsProject.getString("POWNER"));
        project.setBuilt(rsProject.getString("BUNIT"));
        project.setBcontact(rsProject.getString("BCONTACT"));
        project.setTcontact(rsProject.getString("TCONTACT"));
        project.setRcontact(rsProject.getString("RCONTACT"));
        project.setPrecompany(rsProject.getString("PRECOMPANY"));
        project.setPredate(rsProject.getString("PREDATE"));
        project.setPremoney(rsProject.getString("PREMONEY"));
        project.setPresellcontact(rsProject.getString("PRESELLCONTACT"));
        project.setPrebuildcontact(rsProject.getString("PREBUILDCONTACT"));
        project.setPremaintaincontact(rsProject.getString("PREMAINTAINCONTACT"));
        project.setPrefunction(rsProject.getString("PREFUNCTION"));
        project.setPremachine(rsProject.getString("PREMACHINE"));
        project.setPreuseip(rsProject.getString("PREUSEIP"));
        project.setPretestip(rsProject.getString("PRETESTIP"));
        project.setPrerelation(rsProject.getString("PRERELATION"));
        project.setNewcompany(rsProject.getString("NEWCOMPANY"));
        project.setNewdate(rsProject.getString("NEWDATE"));
        project.setNewmoney(rsProject.getString("NEWMONEY"));
        project.setNewsellcontact(rsProject.getString("NEWSELLCONTACT"));
        project.setNewbuildcontact(rsProject.getString("NEWBUILDCONTACT"));
        project.setNewmaintaincontact(rsProject.getString("NEWMAINTAINCONTACT"));
        project.setNewfunction(rsProject.getString("NEWFUNCTION"));
        project.setNewmachine(rsProject.getString("NEWMACHINE"));
        project.setNewrelation(rsProject.getString("NEWRELATION"));
        cycleQuestions(project,pid);
        group.getProjects().add(project);
        System.out.println("    " + pid + ":" + pname);
      }
      rsProject.close();
      stmt.close();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  public void cycleQuestions(ProjectObject po, String pid) {
      try {
          Statement stmt = conn.createStatement();
          ResultSet rsQuestion = stmt.executeQuery("select * from p2question where pid='" + pid + "' order by id");
          while (rsQuestion.next()) {
              String qid = rsQuestion.getString("id");
              String qname = rsQuestion.getString("qname");
              ProjectQuestion pq= new ProjectQuestion();
              pq.setId(qid);
              pq.setQtype(rsQuestion.getString("qtype"));
              pq.setQname(qname);
              pq.setQtext(rsQuestion.getString("qtext"));
              pq.setQstart(rsQuestion.getString("qstart"));
              pq.setQend(rsQuestion.getString("qend"));
              pq.setQowner(rsQuestion.getString("qowner"));
              pq.setQstatus(rsQuestion.getString("qstatus"));
              pq.setPid(pid);
              pq.setGid(rsQuestion.getString("gid"));
              po.getQuestions().add(pq);
              System.out.println("    " + qid + ":" + qname);
          }
          rsQuestion.close();
          stmt.close();
      } catch (Exception e) {
          System.out.println(e.getMessage());
      }
  }

  public ProjectLibrary readByDb() {
    connDb();
    cycleGroup();
    disconnDb();
    return library;
  }

  public static void main(String[] args) {
    PmReadDb pmReadDb = new PmReadDb();
    System.out.println(pmReadDb.readByDb().toString());
  }

}
