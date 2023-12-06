package com.day15;

public class Main {
    public static void main(String[] args) {
        String directoryToWatch = "testDir";
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