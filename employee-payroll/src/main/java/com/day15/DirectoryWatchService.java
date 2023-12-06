package com.day15;

import java.nio.file.*;
import static java.nio.file.StandardWatchEventKinds.*;
import java.io.IOException;

public class DirectoryWatchService {

    private Path pathToWatch;

    public DirectoryWatchService(String path) {
        this.pathToWatch = Paths.get(path);
    }

    public void startWatching() throws IOException, InterruptedException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        pathToWatch.register(watchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);

        System.out.println("Monitoring directory: " + pathToWatch);

        while (true) {
            WatchKey key = watchService.take();
            for (WatchEvent<?> event : key.pollEvents()) {
                System.out.println("Event kind:" + event.kind() + ". File affected: " + event.context() + ".");
            }
            key.reset();
        }
    }
}