package com.sda.scrum;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class DiskScanner {

    public void getAllFiles(File curDir) {

        File[] filesList = curDir.listFiles();
        for (File f : filesList) {
            if (f.isDirectory())
                getAllFiles(f);
            if (f.isFile()) {
                Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                c.add(Calendar.MONTH, -1);
                long monthAgo = c.getTimeInMillis();
                if (f.length() > 10240000 && f.lastModified() < monthAgo) {
                    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                    System.out.println(f.getName() + ": " + f.length() + " " + format.format(f.lastModified()));
                }
            }
        }
    }
}