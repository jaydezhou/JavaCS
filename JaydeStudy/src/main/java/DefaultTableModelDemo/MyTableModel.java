package DefaultTableModelDemo;

import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel{

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 4)
            return Boolean.class;
        return super.getColumnClass(columnIndex);
    }
}