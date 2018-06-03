package com.sda.scrum;

import java.io.File;

public class DiskScanner {

    public void getAllFiles(File curDir) {

        File[] filesList = curDir.listFiles();
        for (File f : filesList) {
            if (f.isDirectory())
                getAllFiles(f);
            if (f.isFile()) {
                System.out.println(f.getName());
            }
        }
    }
}