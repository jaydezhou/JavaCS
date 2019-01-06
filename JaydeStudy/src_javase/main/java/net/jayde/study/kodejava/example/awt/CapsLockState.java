package net.jayde.study.kodejava.example.awt;

import java.awt.*;
import java.awt.event.KeyEvent;

public class CapsLockState {
    public static void main(String[] args) {
        //
        // Get the locking state of the Caps Lock button. If it is "on" this method
        // return boolean true value.
        //
        boolean isOn = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK);

        System.out.println("CapsLock button is " + (isOn ? "on" : "off"));
    }
}
