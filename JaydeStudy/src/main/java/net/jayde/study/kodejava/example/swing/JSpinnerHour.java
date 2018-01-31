package net.jayde.study.kodejava.example.swing;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class JSpinnerHour extends JFrame {
    public JSpinnerHour() {
        initializeUI();
    }

    private void initializeUI() {
        setSize(200, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //
        // The following spinner model will have current date as its
        // value and using hour of day as the calendar field. The start
        // and end comparable has a null values which mean it doesn't
        // have minimum or maximum value.
        //
        SpinnerDateModel model = new SpinnerDateModel(new Date(), null,
                null, Calendar.HOUR_OF_DAY);

        JSpinner spinner = new JSpinner(model);

        //
        // Reformat the display of our spinner to show only the hour
        // and minute information part.
        //
        JFormattedTextField textField =
                ((JSpinner.DefaultEditor) spinner.getEditor())
                        .getTextField();
        DefaultFormatterFactory dff =
                (DefaultFormatterFactory) textField.getFormatterFactory();
        DateFormatter formatter = (DateFormatter) dff.getDefaultFormatter();
        formatter.setFormat(new SimpleDateFormat("hh:mm a"));

        getContentPane().add(spinner, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new JSpinnerHour().setVisible(true);
            }
        });
    }
}
