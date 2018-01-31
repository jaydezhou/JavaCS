package net.jayde.app.music.ui;

import net.jayde.app.music.pojo.MusicPerson;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Vector;

public class MusicGroupPersonsTableModel extends AbstractTableModel{
    //0-id,1-name,2-favourite
    String tableHeads[] = {"id", "姓名", "喜爱"};
    public List<MusicPerson> listDatas;
    String[][] datas;
    public Vector titles;

    public MusicGroupPersonsTableModel() {
        super();
        titles = new Vector(1, 1);
        for (
                int i = 0;
                i < tableHeads.length; i++) {
            titles.add(tableHeads[i]);
        }
    }


    public int getRowCount() {
        return listDatas.size();
    }

    public int getColumnCount() {
        return titles.size();
    }

    public void setDatas(List<MusicPerson> musicPersonList) {
        if (musicPersonList == null)
            throw new IllegalArgumentException("参数不能为null。");
        this.listDatas = musicPersonList;
        datas = new String[musicPersonList.size()][3];
        for(int rowindex =0;rowindex<musicPersonList.size();rowindex++){
            MusicPerson person = musicPersonList.get(rowindex);
            datas[rowindex][0] = person.getId();
            datas[rowindex][1] = person.getName();
            datas[rowindex][2] = person.getFavourite()+"";
        }
        fireTableDataChanged();
    }

//    @Override
//    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
//        MusicPerson person = (MusicPerson) aValue;
//        switch (columnIndex) {
//            case 0:
//
//        }
//        datas[rowIndex][columnIndex] = aValue;
//        fireTableCellUpdated(rowIndex, columnIndex);
//    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        MusicPerson person = listDatas.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return person.getId();
            case 1:
                return person.getName();
            case 2:
                JCheckBox jcb = new JCheckBox();
                if (person.getFavourite() == 1) {
                    jcb.setSelected(true);
                } else {
                    jcb.setSelected(false);
                }
//                return jcb;
                return person.getFavourite();
        }
        return null;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return tableHeads[columnIndex];
    }
}
