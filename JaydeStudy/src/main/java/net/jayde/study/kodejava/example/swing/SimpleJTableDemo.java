package net.jayde.study.kodejava.example.swing;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public class SimpleJTableDemo extends JFrame {
    public SimpleJTableDemo() throws HeadlessException {
        initializeUI();
    }

    private void initializeUI() {
        //
        // Defines table's column names.
        //
        String[] columnNames = {
                "ID", "Name", "Date of Birth", "Sex"
        };
        //
        // Defines table's data.
        //
        Object[][] rowData = {
                {1, "Alice", createDOB(1980, 1, 1), "F"},
                {2, "Bob", createDOB(1982, 6, 21), "M"},
                {3, "Carol", createDOB(1970, 10, 12), "M"},
                {4, "Mallory", createDOB(1988, 2, 19), "M"}
        };

        //
        // Initializes an instance of JTable and specifies the table
        // data and column names. After that we place the table in a
        // scroll pane.
        //
        JTable table = new JTable(rowData, columnNames);
        JScrollPane pane = new JScrollPane(table);

        //
        // Sets the frame setting.
        //
        setTitle("Simple JTable Demo");
        setSize(new Dimension(400, 200));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        getContentPane().add(pane, BorderLayout.CENTER);
    }

    private Date createDOB(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SimpleJTableDemo().setVisible(true);
            }
        });
    }
}
