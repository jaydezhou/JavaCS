package net.jayde.app.pm.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

public class TableTEST extends JFrame {

  //////////////////// 数据源//////////////////////////

  String tableHeads[] = {"A", "B", "C", "D", "E"};
  String tableContent[][] = {{"abc", "def", "ghi", "jkl", "mno"}, {"pqr", "stu", "vwx", "yza", "bcd"},
      {"efg", "hij", "klm", "nop", "qrs"}, {"tuv", "wxy", "zab", "cde", "fgh"}, {"ijk", "lmn", "opq", "rst", "uvw"}}; ////////////////////////////////////////////////////
  JTextField txtboxRow = new JTextField(10);
  JTextField txtboxCol = new JTextField(10);
  JTextField txtboxContent = new JTextField(10);
  JPanel pnlSouth = new JPanel(new GridLayout(1, 6));
  MyTableModel tbModel = new MyTableModel();
  // 生成模型类对象

  public TableTEST() { // 初始化窗体 super("DO A Test!");
    this.setBounds(50, 50, 500, 400);
    // 获取contentPane
    Container contentPane = getContentPane();
    contentPane.setLayout(new BorderLayout());
    // 初始化表格数据及表头
    tbModel.data = new Vector(1, 1);
    for (int i = 0; i < 5; i++)
      for (int j = 0; j < 5; j++) {
        tbModel.data.add(tableContent[i][j]);
      }
    tbModel.titles = new Vector(1, 1);
    for (int i = 0; i < 5; i++) {
      tbModel.titles.add(tableHeads[i]);
    } // 使用表模型对象生成表
    JTable myTable = new JTable(tbModel);
    // 窗体组件布局
    myTable.setAutoResizeMode(5);
    JScrollPane jspCenter = new JScrollPane(myTable);
    contentPane.add(jspCenter, BorderLayout.CENTER);
    pnlSouth.add(new JLabel("Row:"), BorderLayout.SOUTH);
    pnlSouth.add(txtboxRow, BorderLayout.SOUTH);
    pnlSouth.add(new JLabel("Col:"), BorderLayout.SOUTH);
    pnlSouth.add(txtboxCol, BorderLayout.SOUTH);
    pnlSouth.add(new JLabel("Content:"), BorderLayout.SOUTH);
    pnlSouth.add(txtboxContent, BorderLayout.SOUTH);
    contentPane.add(pnlSouth, BorderLayout.SOUTH);
    // 为表格添加监听器
    myTable.addMouseListener(new MouseAdapter() {
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2)
        // 实现双击
        {
          int row = ((JTable) e.getSource()).rowAtPoint(e.getPoint()); // 获得行位置
          int col = ((JTable) e.getSource()).columnAtPoint(e.getPoint()); // 获得列位置
          String cellVal = (String) (tbModel.getValueAt(row, col)); // 获得点击单元格数据
          txtboxRow.setText((row + 1) + "");
          txtboxCol.setText((col + 1) + "");
          txtboxContent.setText(cellVal);
        } else
          return;
      }
    });

    this.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent me) {
        System.exit(0);
      }
    });
    this.setVisible(true);
  }

  public static void main(String Str[]) {
    TableTEST frm = new TableTEST();
  }
}


class MyTableModel extends AbstractTableModel {
  public Vector data;
  public Vector titles;

  public int getRowCount() {
    return data.size() / getColumnCount();
  }

  public int getColumnCount() {
    return titles.size();
  }

  public Object getValueAt(int rowIndex, int columnIndex) {
    return data.get((rowIndex * getColumnCount()) + columnIndex);
  }
}
