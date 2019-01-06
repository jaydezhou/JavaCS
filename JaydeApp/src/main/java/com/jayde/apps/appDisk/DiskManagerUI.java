package com.jayde.apps.appDisk;

import javax.swing.*;

public class DiskManagerUI extends JFrame {
    DiskToXmlPanel diskToXmlPanel = new DiskToXmlPanel();
    public DiskManagerUI() {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 768);
        getContentPane().add(diskToXmlPanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        DiskManagerUI diskManagerUI = new DiskManagerUI();
    }
}
