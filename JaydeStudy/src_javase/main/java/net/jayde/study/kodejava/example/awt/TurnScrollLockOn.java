package net.jayde.study.kodejava.example.awt;

import java.awt.*;
import java.awt.event.KeyEvent;

public class TurnScrollLockOn {
    public static void main(String[] args) {
        //
        // Gets the default toolkit.
        //
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        //
        // Update the locking state for scroll lock button to true
        // will turn the scroll lock on.
        //
        toolkit.setLockingKeyState(KeyEvent.VK_SCROLL_LOCK, Boolean.TRUE);
    }
}
