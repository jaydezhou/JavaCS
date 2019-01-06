package com.jayde.util.diskutils;

import java.io.File;
import java.io.FileFilter;

public class OnlyDirectory implements FileFilter {
    @Override
    public boolean accept(File pathname) {
        return pathname.isDirectory();
    }
}
