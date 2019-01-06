package net.jayde.study.kodejava.example.awt;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

public class RunningDefaultMailClient {
    public static void main(String[] args) {
        //
        // Get an instance of Desktop. If the platform doesn't
        // support Desktop API an UnsupportedOperationException
        // will be thrown.
        //
        Desktop desktop = Desktop.getDesktop();

        try {
            //
            // Open user-default mail client application.
            //
            desktop.mail();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            //
            // Open user-default mail client with the email message
            // fields information.
            //
            // mailto:dummy@domain.com?cc=test@domain.com&
            // subject=First%20Email&&body=Hello%20World
            //
            String message = "mailto:dummy@domain.com?subject=First%20Email";
            URI uri = URI.create(message);
            desktop.mail(uri);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
