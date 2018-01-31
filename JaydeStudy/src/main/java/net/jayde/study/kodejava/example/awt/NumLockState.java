package net.jayde.study.kodejava.example.awt;

import java.awt.*;
import java.awt.event.KeyEvent;

public class NumLockState {
    public static void main(String[] args) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        //
        // Get the locking state of the Num Lock button. If it is "on"
        // this method return boolean true value.
        //
        boolean isOn = toolkit.getLockingKeyState(KeyEvent.VK_NUM_LOCK);

        System.out.println("NumLock button is " + (isOn ? "on" : "off"));
    }
}
