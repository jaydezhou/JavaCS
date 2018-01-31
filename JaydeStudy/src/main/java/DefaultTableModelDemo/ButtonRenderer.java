package DefaultTableModelDemo;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ButtonRenderer extends JButton implements TableCellRenderer {

    public JComponent getTableCellRendererComponent(JTable table, Object value,
                                                    boolean isSelected, boolean hasFocus, int row, int column) {
        //value 源于editor
        String text = (value == null) ? "" : value.toString();
        //按钮文字
        setText(text);
        //单元格提示
        setToolTipText(text);
        //背景色
        setBackground(Color.BLACK);
        //前景色
        setForeground(Color.green);
        return this;
    }
}