package net.jayde.study.kodejava.example.io;

import java.io.File;

public class FileExtension {
    public static final String EXT_SEPARATOR = ".";

    public static void main(String[] args) {
        File file = new File("data.txt");
        String ext = FileExtension.getFileExtension(file);
        System.out.println("Ext = " + ext);

        file = new File("C:\\Data\\Employee\\2010.tar.gz");
        ext = FileExtension.getFileExtension(file);
        System.out.println("Ext = " + ext);

        file = new File("/home/wsaryada/kodejava.org/FileDemo.java");
        ext = FileExtension.getFileExtension(file);
        System.out.println("Ext = " + ext);
    }

    /**
     * Get the extension of the specified file.
     *
     * @param file a file.
     * @return the extension of the file.
     */
    public static String getFileExtension(File file) {
        if (file == null) {
            return null;
        }

        String name = file.getName();
        int extIndex = name.lastIndexOf(FileExtension.EXT_SEPARATOR);

        if (extIndex == -1) {
            return "";
        } else {
            return name.substring(extIndex + 1);
        }
    }
}
