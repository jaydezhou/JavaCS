package net.jayde.app.pm.ui;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import net.jayde.app.pm.pojo.ProjectObject;

public class PmInfoPanel extends JPanel {

  private static Logger logger = LogManager.getLogger(PmInfoPanel.class.getName());
  JTextArea pmTextArea = new JTextArea("null");
  ProjectObject pmObject = null;

  public ProjectObject getPmObject() {
    return pmObject;
  }

  public void setPmObject(ProjectObject inputPmObject) {
    pmObject = inputPmObject;
    pmTextArea.setText(pmObject.showProjectInfo());
  }

  public JTextArea getPmTextArea() {
    return pmTextArea;
  }

  public void setPmTextArea(JTextArea pmTextArea) {
    this.pmTextArea = pmTextArea;
  }

  public PmInfoPanel() {
    setLayout(new BorderLayout());
    add(pmTextArea,BorderLayout.CENTER);
  }
}
