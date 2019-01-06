package net.jayde.study.kodejava.example.swing;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ComboBoxSelectionChange extends JFrame {
    public ComboBoxSelectionChange() {
        initialize();
    }

    private void initialize() {
        setSize(300, 300);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] items = new String[]{"A", "B", "C", "D", "E", "F"};
        JComboBox comboBox = new JComboBox(items);

        final JTextArea textArea = new JTextArea(5, 15);
        textArea.setBorder(new BevelBorder(BevelBorder.LOWERED));

        //
        // For listening to the changes of the selected items in the combo box
        // we need to add an ItemListener to the combo box component as shown
        // below.
        //
        comboBox.addItemListener(new ItemListener() {
            //
            // Listening if a new items of the combo box has been selected.
            //
            public void itemStateChanged(ItemEvent event) {
                JComboBox comboBox = (JComboBox) event.getSource();

                // The item affected by the event.
                Object item = event.getItem();

                textArea.setText("Affected items: " + item.toString());

                if (event.getStateChange() == ItemEvent.SELECTED) {
                    textArea.setText(item.toString() + " selected.");
                }

                if (event.getStateChange() == ItemEvent.DESELECTED) {
                    textArea.setText(item.toString() + " deselected.");
                }
            }
        });

        getContentPane().add(comboBox);
        getContentPane().add(textArea);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ComboBoxSelectionChange().setVisible(true);
            }
        });
    }
}
