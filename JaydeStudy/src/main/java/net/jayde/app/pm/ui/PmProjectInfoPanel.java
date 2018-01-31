package net.jayde.app.pm.ui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import net.jayde.app.pm.pojo.ProjectObject;

public class PmProjectInfoPanel extends JPanel {
  private static Logger logger = LogManager.getLogger(PmProjectInfoPanel.class.getName());

  ProjectObject currentPO = null;
  GridBagLayout gbl = new GridBagLayout();
  GridBagConstraints gbc = new GridBagConstraints();

  JTextField jtfName = new JTextField();
  JTextField jtfId = new JTextField();
  JTextField jtfBunit = new JTextField();
  JTextField jtfPowner = new JTextField();
  JTextField jtfGid = new JTextField();
  JTextField jtfBcontact = new JTextField();
  JTextField jtfTcontact = new JTextField();
  JTextField jtfRcontact = new JTextField();
  JTextField jtfPreCompany = new JTextField();
  JTextField jtfPreDate = new JTextField();
  JTextField jtfPreMoney = new JTextField();
  JTextField jtfPreSellContact = new JTextField();
  JTextField jtfPreBuildContact = new JTextField();
  JTextField jtfPreMaintainContact = new JTextField();
  JTextField jtfPreFunction = new JTextField();
  JTextField jtfPreMachine = new JTextField();
  JTextField jtfPreUseip = new JTextField();
  JTextField jtfPreTestip = new JTextField();
  JTextField jtfNewCompany = new JTextField();
  JTextField jtfNewDate = new JTextField();
  JTextField jtfNewMoney = new JTextField();
  JTextField jtfNewSellContact = new JTextField();
  JTextField jtfNewBuildContact = new JTextField();
  JTextField jtfNewMaintainContact = new JTextField();
  JTextField jtfNewFunction = new JTextField();
  JTextField jtfNewMachine = new JTextField();
  JTextField jtfNewUseip = new JTextField();
  JTextField jtfNewTestip = new JTextField();

  JButton jbSave = new JButton("save");

  public PmProjectInfoPanel() {
    setLayout(gbl);
    gbc.fill = GridBagConstraints.BOTH;
    gbc.ipadx = 10;
    gbc.ipady = 10;
    gbc.weightx = 1;
    gbc.weighty = 1;

    addGridWithTitle(0, 1, 3, 1, jtfName, "name");
    jtfId.setEditable(false);
    addGridWithTitle(4, 1, 1, 1, jtfId, "id");

    addGridWithTitle(0, 2, 1, 1, jtfBunit, "bunit");
    addGridWithTitle(2, 2, 1, 1, jtfPowner, "powner");
    jtfGid.setEditable(false);
    addGridWithTitle(4, 2, 1, 1, jtfGid, "gid");

    addGridWithTitle(0, 3, 5, 1, jtfBcontact, "bcontact");

    addGridWithTitle(0, 4, 5, 1, jtfTcontact, "tcontact");

    addGridWithTitle(0, 5, 5, 1, jtfRcontact, "rcontact");

    addGrid(0, 6, 6, 1, new JSeparator());

    addGridWithTitle(0, 7, 5, 1, jtfPreCompany, "precompany");
    addGridWithTitle(0, 8, 2, 1, jtfPreDate, "predate");
    addGridWithTitle(3, 8, 2, 1, jtfPreMoney, "premoney");
    addGridWithTitle(0, 9, 5, 1, jtfPreSellContact, "presellcontact");
    addGridWithTitle(0, 10, 5, 1, jtfPreBuildContact, "prebuildcontact");
    addGridWithTitle(0, 11, 5, 1, jtfPreMaintainContact, "premaintaincontact");
    addGridWithTitle(0, 12, 5, 1, jtfPreFunction, "prefunction");
    addGridWithTitle(0, 13, 5, 1, jtfPreMachine, "premachine");
    addGridWithTitle(0, 14, 2, 1, jtfPreUseip, "preuseip");
    addGridWithTitle(3, 14, 2, 1, jtfPreTestip, "pretestip");

    addGrid(0, 15, 6, 1, new JSeparator());

    addGridWithTitle(0, 16, 5, 1, jtfNewCompany, "newcompany");
    addGridWithTitle(0, 17, 2, 1, jtfNewDate, "newdate");
    addGridWithTitle(3, 17, 2, 1, jtfNewMoney, "newmoney");
    addGridWithTitle(0, 18, 5, 1, jtfNewSellContact, "newsellcontact");
    addGridWithTitle(0, 19, 5, 1, jtfNewBuildContact, "newbuildcontact");
    addGridWithTitle(0, 20, 5, 1, jtfNewMaintainContact, "newmaintaincontact");
    addGridWithTitle(0, 21, 5, 1, jtfNewFunction, "newfunction");
    addGridWithTitle(0, 22, 5, 1, jtfNewMachine, "newmachine");
    // addGridWithTitle(0, 23, 2, 1, jtfNewUseip, "newuseip");
    // addGridWithTitle(3, 23, 2, 1, jtfNewTestip, "newretestip");

    addGrid(0, 24, 6, 1, new JSeparator());

    addGrid(3,25,1,1,jbSave);
  }

  private void addGridWithTitle(int x, int y, int w, int h, Component c, String title) {
    gbc.gridy = y;
    gbc.gridx = x;
    gbc.gridwidth = 1;
    gbc.gridheight = 1;
    JLabel jLabel = new JLabel(title);
    gbl.setConstraints(jLabel, gbc);
    add(jLabel);

    gbc.gridy = y;
    gbc.gridx = x + 1;
    gbc.gridwidth = w;
    gbc.gridheight = h;
    gbl.setConstraints(c, gbc);
    add(c);
  }

  private void addGrid(int x, int y, int w, int h, Component c) {
    gbc.gridy = y;
    gbc.gridx = x;
    gbc.gridwidth = w;
    gbc.gridheight = h;
    gbl.setConstraints(c, gbc);
    add(c);
  }

  public void setProjectObject(ProjectObject po) {
    currentPO = po;
    jbSave.setEnabled(false);

    jtfName.setText(po.getPname());
    jtfId.setText(po.getId());
    jtfBunit.setText(po.getBuilt());
    jtfPowner.setText(po.getPowner());
    jtfGid.setText(po.getGid());
    jtfBcontact.setText(po.getBcontact());
    jtfTcontact.setText(po.getTcontact());
    jtfRcontact.setText(po.getRcontact());
    jtfPreCompany.setText(po.getPrecompany());
    jtfPreDate.setText(po.getPredate());
    jtfPreMoney.setText(po.getPremoney());
    jtfPreSellContact.setText(po.getPresellcontact());
    jtfPreBuildContact.setText(po.getPrebuildcontact());
    jtfPreMaintainContact.setText(po.getPremaintaincontact());
    jtfPreFunction.setText(po.getPrefunction());
    jtfPreMachine.setText(po.getPremachine());
    jtfPreUseip.setText(po.getPreuseip());
    jtfPreTestip.setText(po.getPretestip());
    jtfNewCompany.setText(po.getNewcompany());
    jtfNewDate.setText(po.getNewdate());
    jtfNewMoney.setText(po.getNewmoney());
    jtfNewSellContact.setText(po.getNewsellcontact());
    jtfNewBuildContact.setText(po.getNewbuildcontact());
    jtfNewMaintainContact.setText(po.getNewmaintaincontact());
    jtfNewFunction.setText(po.getNewfunction());
    jtfNewMachine.setText(po.getNewmachine());
  }
}
