package org.kodejava.example.util;

import java.io.*;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnzipWithChecksum {
    public static void main(String[] args) {
        String zipname = "data-1.zip";

        try {
            FileInputStream fis = new FileInputStream(zipname);

            //
            // Creating input stream that also maintains the checksum of the
            // data which later can be used to validate data integrity.
            //
            CheckedInputStream checksum = new CheckedInputStream(fis, new Adler32());
            ZipInputStream zis = new ZipInputStream(new BufferedInputStream(checksum));
            ZipEntry entry;

            //
            // Read each entry from the ZipInputStream until no more entry found
            // indicated by a null return value of the getNextEntry() method.
            //
            while ((entry = zis.getNextEntry()) != null) {
                System.out.println("Unzipping: " + entry.getName());

                int size;
                byte[] buffer = new byte[2048];

                FileOutputStream fos = new FileOutputStream(entry.getName());
                BufferedOutputStream bos = new BufferedOutputStream(fos, buffer.length);

                while ((size = zis.read(buffer, 0, buffer.length)) != -1) {
                    bos.write(buffer, 0, size);
                }
                bos.flush();
                bos.close();
            }

            zis.close();
            fis.close();

            //
            // Print out the checksum value
            //
            System.out.println("Checksum = " + checksum.getChecksum().getValue());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
