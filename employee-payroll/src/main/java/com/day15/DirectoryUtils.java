package com.day15;

import java.io.File;

public class DirectoryUtils {

    public static int countEntriesInDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.isDirectory()) {
            System.out.println("Not a directory.");
            return 0;
        }

        File[] files = directory.listFiles();
        if (files != null) {
            return files.length;
        }

        return 0;
    }
}