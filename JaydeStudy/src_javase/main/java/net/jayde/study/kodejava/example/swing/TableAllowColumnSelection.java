package net.jayde.study.kodejava.example.swing;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;

public class TableAllowColumnSelection extends JPanel {
    public TableAllowColumnSelection() {
        initializePanel();
    }

    private void initializePanel() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(500, 150));

        JTable table = new JTable(new PremiereLeagueTableModel());
        //
        // sets to false to disallow row selection in the table
        // model.
        //
        table.setRowSelectionAllowed(false);

        //
        // Sets to true to allow column selection in the table
        // model.
        //
        table.setColumnSelectionAllowed(true);

        JScrollPane pane = new JScrollPane(table);
        this.add(pane, BorderLayout.CENTER);
    }

    public static void showFrame() {
        JPanel panel = new TableAllowColumnSelection();
        panel.setOpaque(true);

        JFrame frame = new JFrame("JTable Column Selection");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TableAllowColumnSelection.showFrame();
            }
        });
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

        public int getRowCount() {
            return data.length;
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            return data[rowIndex][columnIndex];
        }
    }
}
