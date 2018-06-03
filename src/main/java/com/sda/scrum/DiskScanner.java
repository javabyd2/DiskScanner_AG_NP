package com.sda.scrum;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

public class DiskScanner {

    public void getAllFiles(File curDir) {

        File[] filesList = curDir.listFiles();
        Map<String, Long> filesMap = new TreeMap<>();

        for (File f : filesList) {
            if (f.isDirectory())
                getAllFiles(f);
            if (f.isFile()) {
                Calendar c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                c.add(Calendar.MONTH, -1);
                long monthAgo = c.getTimeInMillis();
                if (f.length() > 10240000 && f.lastModified() < monthAgo) {
                    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

                    filesMap.put(f.getName() + " modified: " + format.format(f.lastModified()), f.length());

                    for (Map.Entry entry : filesMap.entrySet()) {
                        System.out.println(entry.getKey() + " " + entry.getValue());
                    }


                //    System.out.println(f.getName() + ": " + f.length() + " " + format.format(f.lastModified()));
                }
            }
        }
    }
}