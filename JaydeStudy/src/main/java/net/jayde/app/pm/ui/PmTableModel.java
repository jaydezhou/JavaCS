package net.jayde.app.pm.ui;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import net.jayde.app.pm.pojo.ProjectQuestion;

public class PmTableModel extends AbstractTableModel {

    public Vector data;
    public Vector titles;

    @Override
    public int getRowCount() {
        return data.size() / getColumnCount();
    }

    @Override
    public int getColumnCount() {
        return titles.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get((rowIndex * getColumnCount()) + columnIndex);
    }

    public void setTitlesVector(Vector<String> v){
        for (String title:v) {
            System.out.println(title);
           titles.add(title);
        }
    }

    public void setQuestionVector(Vector<ProjectQuestion> v){
        for (ProjectQuestion pq:v) {
            System.out.println(pq);
            data.add(pq.getQtype());
            data.add(pq.getQname());
            data.add(pq.getQtext());
            data.add(pq.getQstart());
            data.add(pq.getQend());
            data.add(pq.getQowner());
            data.add(pq.getQstatus());
        }
    }
    @Override
    public String getColumnName(int column) {
        return (String)titles.get(column);
    }


}
