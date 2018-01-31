package net.jayde.study.kodejava.example.awt;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

public class RunningDefaultBrowser {
    public static void main(String[] args) {
        URI uri = URI.create("http://www.kodejava.org");
        try {
            //
            // Get Desktop instance of the current browser context.
            // If the platform doesn't support Desktop API an
            // UnsupportedOperationException will be thrown.
            //
            Desktop desktop = Desktop.getDesktop();

            //
            // Browse the uri using user's default web browser.
            //
            desktop.browse(uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
