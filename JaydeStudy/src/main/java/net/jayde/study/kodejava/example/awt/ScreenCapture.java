package net.jayde.study.kodejava.example.awt;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ScreenCapture {
    public static void main(String[] args) {
        try {
            Robot robot = new Robot();

            //
            // Capture screen from the top left in 200 by 200 pixel size.
            //
            BufferedImage bufferedImage = robot.createScreenCapture(
                    new Rectangle(new Dimension(200, 200)));

            //
            // The captured image will the writen into a file called
            // screenshot.png
            //
            File imageFile = new File("screenshot.png");
            ImageIO.write(bufferedImage, "png", imageFile);
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
