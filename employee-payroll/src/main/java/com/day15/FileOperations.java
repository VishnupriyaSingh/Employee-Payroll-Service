package com.day15;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileOperations {

    public boolean checkFileExists(String path) {
        File file = new File(path);
        return file.exists();
    }

    public boolean deleteFile(String path) {
        File file = new File(path);
        return file.delete();
    }

    public boolean createDirectory(String path) {
        File dir = new File(path);
        return dir.mkdir();
    }

    public boolean createEmptyFile(String path) throws IOException {
        File file = new File(path);
        return file.createNewFile();
    }

    public List<String> listFiles(String directoryPath, String extension) {
        File dir = new File(directoryPath);
        File[] files = dir.listFiles((dir1, name) -> name.toLowerCase().endsWith(extension));

        List<String> fileNames = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                fileNames.add(file.getName());
            }
        }
        return fileNames;
    }
}