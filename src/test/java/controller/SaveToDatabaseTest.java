package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SaveToDatabaseTest {

    private SaveToDatabase saveToDatabase;
    private String testFilePath;

    @BeforeEach
    void setUp() {
        saveToDatabase = new SaveToDatabase();
        // Set up a temporary test file path
        testFilePath = "path/to/your/test_output.csv";
    }

    @Test
    void testExportDataToCSV() {
        // Call the method to export data to CSV
        saveToDatabase.exportDataToCSV(testFilePath);

        // Verify that the file was created
        File outputFile = new File(testFilePath);
        assertTrue(outputFile.exists(), "Output file should exist");

        // Read the content of the file and verify its correctness
        try {
            List<String> lines = Files.readAllLines(Path.of(testFilePath));
            assertEquals(2, lines.size(), "CSV file should have 2 lines (header + data)");

            // Verify the header
            assertEquals("UserID,MovieID,ShowID,ActorID,DirectorID,GenreID,PreferenceID", lines.get(0));

            // Verify the example data row
            assertEquals("1,101,0,201,301,401,501", lines.get(1));
        } catch (IOException e) {
            fail("Error reading test output file: " + e.getMessage());
        }
    }
}
// Path: src/test/java/controller/SaveToDatabaseTest.java