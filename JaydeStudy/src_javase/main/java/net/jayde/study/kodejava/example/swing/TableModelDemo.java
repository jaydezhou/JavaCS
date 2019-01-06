package net.jayde.study.kodejava.example.swing;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;

public class TableModelDemo extends JPanel {
    public TableModelDemo() {
        initializePanel();
    }

    private void initializePanel() {
        //
        // Creates an instance of PremiereLeagueTableModel
        //
        PremiereLeagueTableModel tableModel =
                new PremiereLeagueTableModel();

        //
        // Creates an instance of JTable with a TableModel
        // as the constructor parameters.
        //
        JTable table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);

        this.setPreferredSize(new Dimension(500, 200));
        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);
    }

    public static void showFrame() {
        JPanel panel = new TableModelDemo();
        panel.setOpaque(true);

        JFrame frame = new JFrame("Premiere League - 2010/2011");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }

    class PremiereLeagueTableModel extends AbstractTableModel {
        // TableModel's column names
        private String[] columnNames = {
                "TEAM", "P", "W", "D", "L", "GS", "GA", "GD", "PTS"
        };

        // TableModel's data
        private Object[][] data = {
                {"Chelsea", 5, 5, 0, 0, 21, 1, 20, 15},
                {"Arsenal", 5, 3, 2, 0, 14, 4, 10, 11},
                {"Manchester United", 5, 3, 2, 0, 14, 7, 7, 11},
                {"Manchester City", 5, 2, 2, 1, 6, 2, 4, 8},
                {"Tottenham Hotspur", 5, 2, 2, 1, 6, 4, 2, 8}
        };

        /**
         * Returns the number of rows in the table model.
         */
        public int getRowCount() {
            return data.length;
        }

        /**
         * Returns the number of columns in the table model.
         */
        public int getColumnCount() {
            return columnNames.length;
        }

        /**
         * Returns the column name for the column index.
         */
        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        /**
         * Returns data type of the column specified by its index.
         */
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return getValueAt(0, columnIndex).getClass();
        }

        /**
         * Returns the value of a table model at the specified
         * row index and column index.
         */
        public Object getValueAt(int rowIndex, int columnIndex) {
            return data[rowIndex][columnIndex];
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                showFrame();
            }
        });
    }
}
