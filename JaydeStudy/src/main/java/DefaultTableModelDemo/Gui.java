package DefaultTableModelDemo;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Gui extends JPanel {

    private Object[] tmpRow = {"tmpName", "tmpDescription"};
    private MyJTable table;
    private JButton addBtn;
    private JButton delBtn;
    private MyTableModel model ;

    public Gui() {
        table = new MyJTable();
        table.setPreferredScrollableViewportSize(new Dimension(500, 300));
        table.setFillsViewportHeight(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);
        //scrollPane.setPreferredSize(new Dimension(500, 600));
        //scrollPane.set
        //Add the scroll pane to this panel.
        add(scrollPane);
        //set tableModel and data
        model = new MyTableModel();
        String[] columnNames = {"Name",
                "Description",
                "Sport",
                "# of Years",
                "Vegetarian"};
        Object[][] data = {
                {"Kathy", "Smith",
                        "Snowboarding", new Integer(5), new Boolean(false)},
                {"John", "Doe",
                        "Rowing", new Integer(3), new Boolean(true)},
                {"Sue", "Black",
                        "Knitting", new Integer(2), new Boolean(false)},
                {"Jane", "White",
                        "Speed reading", new Integer(20), new Boolean(true)},
                {"Joe", "Brown",
                        "Pool", new Integer(10), new Boolean(false)}
        };
        model.setDataVector(data, columnNames);
        table.setModel(model);
        //添加渲染器
        table.getColumn("Name").setCellRenderer(new ButtonRenderer());
        //添加编辑器
        table.getColumn("Name").setCellEditor( new ButtonEditor());
        //添加按钮
        addBtn = new JButton("增加");
        addBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                model.addRow(tmpRow);
            }
        });

        delBtn = new JButton("删除");
        delBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                // TODO Auto-generated method stub
                int rowIndex = table.getSelectedRow();
                if(rowIndex != -1)
                    model.removeRow(rowIndex);
            }
        });

        add(addBtn);
        add(delBtn);

        addDataChangeListener();

        //设置列
        setSportsColumn();
    }

    private void setSportsColumn(){
        String [] itmes = {"Snowboarding", "Rowing", "Knitting", "Speed reading", "Pool"};
        JComboBox<String> comboBox = new JComboBox<String>(itmes);
        DefaultTableCellRenderer renderer =
                new DefaultTableCellRenderer();
        renderer.setToolTipText("Click for combo box");
        setColumn("Sport", comboBox, renderer);
        TableColumn col = table.getColumn("Sport");
        //setToolTipText("favorit sport is " + );
    }

    public void setColumn(String colName, Object editor, Object renderer) {
        int index = table.getColumnModel().getColumnIndex(colName);
        TableColumn modeColumn = table.getColumnModel().getColumn(index);
        if (editor instanceof JComponent) {
            setEditor(modeColumn, (JComponent)editor);
        }
        else if (editor instanceof DefaultCellEditor) {
            modeColumn.setCellEditor((DefaultCellEditor)editor);
        }

        if (renderer instanceof DefaultTableCellRenderer) {
            modeColumn.setCellRenderer((DefaultTableCellRenderer)renderer);
        }
        else if (renderer instanceof ButtonRenderer) {
            modeColumn.setCellRenderer((ButtonRenderer)renderer);
        }
    }

    protected void setEditor(TableColumn column, JComponent component){
        if(component instanceof JTextField )
            column.setCellEditor(new DefaultCellEditor((JTextField) component));
        else if(component instanceof JComboBox )
            column.setCellEditor(new DefaultCellEditor((JComboBox<String>) component));
        else if(component instanceof JCheckBox )
            column.setCellEditor(new DefaultCellEditor((JCheckBox) component));
    }

    private void addDataChangeListener(){
        //检测单元格数据变更
        Action action = new AbstractAction()
        {
            public void actionPerformed(ActionEvent e)
            {
                TableCellListener tcl = (TableCellListener)e.getSource();
                int row = tcl.getRow();
                int col = tcl.getColumn();
                Object oldValue = tcl.getOldValue();
                //if(oldValue == null)
                //oldValue = "";
                Object newValue = tcl.getNewValue();
                //if(newValue == null)
                //newValue = "";
                System.out.printf("cell changed at [%d,%d] : %s -> %s%n",row, col, oldValue, newValue);
            }
        };
        @SuppressWarnings("unused")
        TableCellListener tcl1 = new TableCellListener(table, action);
        System.out.printf("cell changed%n");
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Gui");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        Gui newContentPane = new Gui();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}