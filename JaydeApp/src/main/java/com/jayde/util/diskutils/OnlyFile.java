package com.jayde.util.diskutils;

import java.io.File;
import java.io.FileFilter;

public class OnlyFile implements FileFilter {

    @Override
    public boolean accept(File pathname) {
        return pathname.isFile();
    }
}
