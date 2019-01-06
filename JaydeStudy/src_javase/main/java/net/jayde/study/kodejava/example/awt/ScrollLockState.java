package net.jayde.study.kodejava.example.awt;

import java.awt.*;
import java.awt.event.KeyEvent;

public class ScrollLockState {
    public static void main(String[] args) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        //
        // Get the locking state of the Scroll Lock button. If it is "on"
        // this method return boolean true value.
        //
        boolean isOn = toolkit.getLockingKeyState(KeyEvent.VK_SCROLL_LOCK);

        System.out.println("ScrollLock button is " + (isOn ? "on" : "off"));
    }
}
