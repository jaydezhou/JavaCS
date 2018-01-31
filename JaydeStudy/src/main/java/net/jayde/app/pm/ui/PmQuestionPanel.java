package net.jayde.app.pm.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import net.jayde.app.pm.pojo.ProjectObject;
import net.jayde.app.pm.pojo.ProjectQuestion;

public class PmQuestionPanel extends JPanel {

  private static Logger logger = LogManager.getLogger(PmQuestionPanel.class.getName());
  JTable questTable = new JTable();
  JCheckBox jcbClosed = new JCheckBox("隐藏已完成");
  JCheckBox jcbDelay = new JCheckBox("隐藏未延期");
  JButton jbAdd = new JButton("add");
  JButton jbDel = new JButton("del");
  JButton jbSave = new JButton("save");
//  JFrame rootFrame = null;
//
//  public JFrame getRootFrame() {
//    return rootFrame;
//  }
//
//  public void setRootFrame(JFrame rootFrame) {
//    this.rootFrame = rootFrame;
//  }

  public PmQuestionPanel() {
    setLayout(new BorderLayout());

    add(new JScrollPane(questTable), BorderLayout.CENTER);
    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    buttonPanel.add(jbAdd);
    buttonPanel.add(jbDel);
    buttonPanel.add(jbSave);
    add(buttonPanel, BorderLayout.SOUTH);
    JPanel checkPanel = new JPanel();
    checkPanel.setLayout(new FlowLayout());
    checkPanel.add(jcbClosed);
    checkPanel.add(jcbDelay);
    add(checkPanel, BorderLayout.NORTH);
  }

  public void setTableData(ProjectObject po) {
    Vector<ProjectQuestion> rowDatas;
    Vector<String> columnNames;
    columnNames = new Vector();
    // columnNames.add("id");
    columnNames.add("qtype");
    columnNames.add("qname");
    columnNames.add("qtext");
    columnNames.add("qstart");
    columnNames.add("qend");
    columnNames.add("qowner");
    columnNames.add("qstatus");
    // columnNames.add("gid");
    // columnNames.add("pid");
    rowDatas = new Vector();
    for (ProjectQuestion pq : po.getQuestions()) {
      rowDatas.add(pq);
    }
    PmTableModel tmhavesold = new PmTableModel();// 建立默认的JTable模型
    tmhavesold.titles= new Vector(1,1);
    tmhavesold.setTitlesVector(columnNames);
    tmhavesold.data = new Vector(1,1);
    tmhavesold.setQuestionVector(rowDatas);
    questTable.setModel(tmhavesold);

    // this.updateUI();
    questTable.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if(e.getClickCount()==2){
          System.out.println("双击");
          JDialog jd = new JDialog();
          jd.setModal(true);
          jd.setSize(400,300);
          jd.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
          jd.setVisible(true);
        }
      }
    });
  }
}
