package net.jayde.study.kodejava.example.nio;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author <a href="wayan.saryada@kodesoft.com">I Wayan Saryada</a>
 */
public class FileAttributesDemo {
    public static void main(String[] args) throws Exception {
        String path = "D:\\GitHub\\kodejava\\core-sample\\src\\main\\resources\\data.txt";

        Path file = Paths.get(path);
        BasicFileAttributes attr =
                Files.readAttributes(file, BasicFileAttributes.class);
        System.out.println("creationTime     = " + attr.creationTime());
        System.out.println("lastAccessTime   = " + attr.lastAccessTime());
        System.out.println("lastModifiedTime = " + attr.lastModifiedTime());

        System.out.println("isDirectory      = " + attr.isDirectory());
        System.out.println("isOther          = " + attr.isOther());
        System.out.println("isRegularFile    = " + attr.isRegularFile());
        System.out.println("isSymbolicLink   = " + attr.isSymbolicLink());
        System.out.println("size             = " + attr.size());
    }
}
