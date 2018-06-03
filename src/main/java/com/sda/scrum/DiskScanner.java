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

                long fileSizeInBytes = f.length();
                long fileSizeInKB = fileSizeInBytes / 1024;
                long fileSizeInMB = fileSizeInKB / 1024;
                if (fileSizeInMB > 10 && f.lastModified() < monthAgo) {
                    SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

                    filesMap.put(f.getAbsolutePath() +  " modified: " + format.format(f.lastModified()), fileSizeInMB);

                    for (Map.Entry entry : filesMap.entrySet()) {
                        System.out.println(entry.getKey() + " " + entry.getValue() + "Mb");
                    }


                //    System.out.println(f.getName() + ": " + f.length() + " " + format.format(f.lastModified()));
                }
            }
        }
    }
}