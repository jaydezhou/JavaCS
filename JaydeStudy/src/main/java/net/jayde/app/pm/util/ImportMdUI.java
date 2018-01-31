package net.jayde.app.pm.util;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ImportMdUI extends JPanel {

  private static Logger logger = LogManager.getLogger(ImportMdUI.class.getName());
  JTextArea textArea = new JTextArea();
  JTextArea textField = new JTextArea();
  JButton jButton = new JButton("import");

  public ImportMdUI() {
    setLayout(new BorderLayout());
    add(new JScrollPane(textArea), BorderLayout.CENTER);
    add(textField, BorderLayout.SOUTH);
    add(jButton, BorderLayout.EAST);

    jButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String[] texts = textArea.getText().split("\n");
        String pid="";
        String sql = "update p2project set ";
        for (int i = 0; i < texts.length; i++) {
          String text = texts[i];
          if (i == 0) {
            String pname = text.split("\\|")[2].trim();
            connDb();
            try {
              Statement stmt = conn.createStatement();
              String selectSQL = "select * from p2project where pname = '" + pname + "'";
              ResultSet rs = stmt.executeQuery(selectSQL);
              if (rs.next()) {
                pid = rs.getString("id");
                textField.setText(pid);
              }
              rs.close();
              stmt.close();
            } catch (Exception ee) {
              System.out.println(ee.getMessage());
            }
            disconnDb();
          } else {
            String fields[] = text.split("\\|");
            String fieldName = fields[1].trim();
            String fieldValue = fields[2].trim();
            if (fieldName.length() > 0) {
              if (sql.equals("update p2project set ")) {
                sql += fieldName + "='" + fieldValue + "'";
              } else {
                sql += ", " + fieldName + "='" + fieldValue + "'";
              }
            }
          }
        }
        sql += "\n where id='" + pid + "';";
          System.out.println(sql);
      }
    });
  }


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

  public static void main(String[] args) {
    JFrame jf = new JFrame();
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jf.setSize(800, 600);
    ImportMdUI jpanel = new ImportMdUI();
    jf.getContentPane().add(jpanel);
    jf.setVisible(true);
  }
}
