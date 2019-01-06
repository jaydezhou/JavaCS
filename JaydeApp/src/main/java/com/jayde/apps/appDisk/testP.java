package com.jayde.apps.appDisk;

import javax.swing.*;
import java.awt.*;

public class testP extends JPanel {
    JProgressBar bar = new JProgressBar();

    public testP() {

        setLayout(new BorderLayout());
        add(bar, BorderLayout.CENTER);
    }

    public void runP() throws InterruptedException {
        bar.setValue(0);
        int max = 10000000;
        for (int i = 0; i <= max; i++) {
//            Thread.sleep(1);
            bar.setValue(i*100/max);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        testP p = new testP();
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(500, 500);
        jFrame.getContentPane().add(p);
        jFrame.setVisible(true);
        p.runP();
    }
}
