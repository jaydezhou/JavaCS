package net.jayde.study.kodejava.example.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileListing {
    public static void main(String[] args) {
        File root = new File("D:\\Sandbox");
        List<File> fileList = FileListing.getFileListFor(root);

        for (File file : fileList) {
            System.out.println("file = " + file);
        }
    }

    private static List<File> getFileListFor(File root) {
        validateDirectory(root);
        List<File> fileList = new ArrayList<>();
        File[] filesAndDirs = root.listFiles();
        List<File> filesAndDirsList = Arrays.asList(filesAndDirs);

        for (File file : filesAndDirsList) {
            fileList.add(file);
            if (file.isDirectory()) {
                List<File> subDirList = getFileListFor(file);
                fileList.addAll(subDirList);
            }
        }
        return fileList;
    }

    private static void validateDirectory(File directory) {
        if (directory == null || !directory.exists()
                || !directory.isDirectory() || !directory.canRead()) {
            throw new IllegalArgumentException(
                    "Invalid directory: " + directory);
        }
    }
}
