package DefaultTableModelDemo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class ButtonEditor extends DefaultCellEditor {
    protected JButton button;//represent the  cellEditorComponent
    private String cellValue;//保存cellEditorValue

    public ButtonEditor() {
        super(new JCheckBox());
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(button, cellValue + ": Ouch!");
                //刷新渲染器
                fireEditingStopped();
            }
        });
    }

    public JComponent getTableCellEditorComponent(JTable table, Object value,
                                                  boolean isSelected, int row, int column) {
        //value 源于单元格数值
        cellValue = (value == null) ? "" : value.toString();
        return button;
    }

    public Object getCellEditorValue() {
        return new String(cellValue);
    }
}