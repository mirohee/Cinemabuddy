package controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Utility class for establishing a connection to the database.
 */
public class SaveToDatabase {

    /**
     * Establishes a connection to the MySQL database.
     * @return Connection object if successful, null otherwise.
     */
    public Connection getConnectionToDb() {
        Properties props = new Properties();
        try {
            InputStream in = getClass().getClassLoader().getResourceAsStream("config.properties");
            props.load(in);
        } catch (IOException e) {
            return null;
        }
        String url = props.getProperty("DATABASE_URL");
        String username = props.getProperty("DATABASE_USERNAME");
        String password = props.getProperty("DATABASE_PASSWORD");

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            Logger.getGlobal().severe("Connection failed. Error: " + e.getMessage());
        }
        return connection;
    }
}
