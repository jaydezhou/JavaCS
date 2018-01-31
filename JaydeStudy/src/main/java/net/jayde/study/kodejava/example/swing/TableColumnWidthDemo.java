package net.jayde.study.kodejava.example.swing;

import javax.swing.*;
import javax.swing.table.TableColumn;
import java.awt.*;

public class TableColumnWidthDemo extends JPanel {
    public TableColumnWidthDemo() {
        initializePanel();
    }

    private void initializePanel() {
        //
        // Defines table's column names.
        //
        String[] columnNames = {
                "TEAM", "P", "W", "D", "L", "GS", "GA", "GD", "PTS"
        };

        //
        // Defines table's data.
        //
        Object[][] data = {
                {"Chelsea", 5, 5, 0, 0, 21, 1, 20, 15},
                {"Arsenal", 5, 3, 2, 0, 14, 4, 10, 11},
                {"Manchester United", 5, 3, 2, 0, 14, 7, 7, 11},
                {"Manchester City", 5, 2, 2, 1, 6, 2, 4, 8},
                {"Tottenham Hotspur", 5, 2, 2, 1, 6, 4, 2, 8}
        };

        //
        // Defines table's column width.
        //
        int[] columnsWidth = {
                200, 25, 25, 25, 25, 25, 25, 25, 50
        };

        //
        // Creates an instance of JTable and fill it with data and
        // column names information.
        //
        JTable table = new JTable(data, columnNames);

        //
        // Configures table's column width.
        //
        int i = 0;
        for (int width : columnsWidth) {
            TableColumn column = table.getColumnModel().getColumn(i++);
            column.setMinWidth(width);
            column.setMaxWidth(width);
            column.setPreferredWidth(width);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        this.setLayout(new BorderLayout());
        this.add(scrollPane, BorderLayout.CENTER);
        this.setPreferredSize(new Dimension(500, 200));
    }

    public static void showFrame() {
        JPanel panel = new TableColumnWidthDemo();
        panel.setOpaque(true);

        //
        // Creates and configures the JFrame component for our
        // program.
        //
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.setTitle("English Premiere League - 2010/2011");
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                showFrame();
            }
        });
    }
}
