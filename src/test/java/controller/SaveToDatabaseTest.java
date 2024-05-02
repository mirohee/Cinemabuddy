package controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SaveToDatabaseTest {

    private SaveToDatabase saveToDatabase;

    @BeforeEach
    void setUp() {
        saveToDatabase = new SaveToDatabase();
    }

    @Test
    void testConnectionToDb() {
        try {
            Connection connection = saveToDatabase.getConnectionToDb();

            assertNotNull(connection, "Connection should not be null");
            assertFalse(connection.isClosed(), "Connection should be open");
        } catch (SQLException e) {
            fail("SQLException occurred: " + e.getMessage());
        }
    }
}

