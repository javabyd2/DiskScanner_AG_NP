package com.sda.scrum;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

public class DiskScanner {
    Map<String, Long> filesMap = new TreeMap<>();
    public void getAllFiles(File curDir) {

        File[] filesList = curDir.listFiles();


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

                    filesMap.put(f.getAbsolutePath() + " modified: " + format.format(f.lastModified()), fileSizeInMB);


                    //    System.out.println(f.getName() + ": " + f.length() + " " + format.format(f.lastModified()));
                }

            }
        }


        for (Map.Entry entry : sortByValue(filesMap).entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue() + "Mb");
        }
    }
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
}