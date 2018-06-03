package com.sda.scrum;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        DiskScanner diskScanner = new DiskScanner();

        diskScanner.getAllFiles(new File("/Users/adrian.gielzak"));
    }
}
