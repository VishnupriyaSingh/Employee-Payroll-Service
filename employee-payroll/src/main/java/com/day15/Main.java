package com.day15;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        String directoryToWatch = "C:\\Users\\KIIT\\Desktop\\github_repos\\Employee-Payroll-Service\\employee-payroll\\testDir";
        DirectoryWatchService watchService = new DirectoryWatchService(directoryToWatch);

        try {
            watchService.startWatching();
        } 
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        // Count entries in the directory
        int count = DirectoryUtils.countEntriesInDirectory(directoryToWatch);
        System.out.println("Number of entries in the directory: " + count);
    }
}