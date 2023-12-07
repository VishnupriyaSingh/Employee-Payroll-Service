import com.day15.FileOperations;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

class FileOperationsTest {

    private FileOperations fileOps;
    private String testDirectoryPath = "test_dir";
    private String testFilePath = "test_dir/testFile.txt";

    @BeforeEach
    void setUp() {
        fileOps = new FileOperations();
        new File(testDirectoryPath).mkdirs();
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.walk(Path.of(testDirectoryPath))
                .map(Path::toFile)
                .forEach(File::delete);
    }

    @Test
    void testFileExists() throws IOException {
        Files.createFile(Path.of(testFilePath));
        assertTrue(fileOps.checkFileExists(testFilePath));
    }

    @Test
    void testDeleteFile() throws IOException {
        Files.createFile(Path.of(testFilePath));
        assertTrue(fileOps.deleteFile(testFilePath));
        assertFalse(fileOps.checkFileExists(testFilePath));
    }

    @Test
    void testCreateDirectory() {
        assertTrue(fileOps.createDirectory(testDirectoryPath + "/newDir"));
    }

    @Test
    void testCreateEmptyFile() throws IOException {
        assertTrue(fileOps.createEmptyFile(testFilePath));
    }
}