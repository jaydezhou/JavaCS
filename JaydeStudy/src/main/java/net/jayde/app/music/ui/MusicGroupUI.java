package net.jayde.app.music.ui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.TableColumnModel;

import net.jayde.app.music.dao.mybatisDao.MusicDaoMybatis;
import net.jayde.app.music.pojo.MusicPerson;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import net.jayde.app.music.pojo.MusicGroup;

public class MusicGroupUI extends JPanel {

    private static Logger logger = LogManager.getLogger(MusicGroupUI.class.getName());

    JButton jbReflesh = new JButton("Reflesh");
    JButton jbSave = new JButton("Save");
    JTable tablePersons = null;

    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();

    MusicGroup currentGroup = null;

    public MusicGroupUI() {
        setLayout(new BorderLayout());

        MusicGroupPersonsTableModel model = new MusicGroupPersonsTableModel();

        MusicDaoMybatis dao = new MusicDaoMybatis();
        MusicGroup group = dao.getMusicGroup("11");// 使用表模型对象生成表
        List<MusicPerson> listPersons = dao.getSonMusicPersons(group.getId());
        System.out.println(listPersons.size());
        for(MusicPerson person:listPersons){
            System.out.println(person);
        }
        model.setDatas(listPersons);
        tablePersons = new JTable(model);
        TableColumnModel tcm = tablePersons.getColumnModel();
        tcm.getColumn(2).setCellEditor(new DefaultCellEditor(new JCheckBox()));
model.fireTableDataChanged();

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(jbReflesh, BorderLayout.WEST);
        buttonPanel.add(jbSave, BorderLayout.EAST);
        add(buttonPanel, BorderLayout.NORTH);

        JScrollPane jScrollPane = new JScrollPane(tablePersons);
        add(jScrollPane, BorderLayout.CENTER);
    }

    public MusicGroup getCurrentGroup() {
        return currentGroup;
    }

    public void setCurrentGroup(MusicGroup currentGroup) {
        this.currentGroup = currentGroup;
    }

    public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(800, 600);
        MusicGroupUI musicGroupUI = new MusicGroupUI();
        jf.getContentPane().add(musicGroupUI);
        jf.setVisible(true);
    }
}
