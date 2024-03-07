package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import api.ApiClient;

public class MovieDataHandler {
    public void saveMovieIdToDatabase(String movieTitle) {
        try {
            // Make an API call to search for the movie by title
            String searchResults = ApiClient.searchByAka(movieTitle);

            // Parse the response to extract the movie ID
            String movieId = parseMovieIdFromResponse(searchResults);

            // Connect to the database
            try (Connection connection = getConnectionToDatabase()) {
                // Insert the movie ID into the database
                insertMovieId(connection, movieId);
            }
        } catch (IOException | JSONException | SQLException e) {
            e.printStackTrace();
        }
    }

    private String parseMovieIdFromResponse(String searchResults) throws JSONException {
        JSONObject jsonObject = new JSONObject(searchResults);
        if (jsonObject.has("results")) {
            JSONObject firstResult = jsonObject.getJSONArray("results").getJSONObject(0);
            return firstResult.getString("id");
        }
        return null;
    }

    private Connection getConnectionToDatabase() throws SQLException {
        String url = "jdbc:mysql://mysql.metropolia.fi:3306/mirosaa";
        String username = "mirosaa";
        String password = "1234";
        return DriverManager.getConnection(url, username, password);
    }

    private void insertMovieId(Connection connection, String movieId) throws SQLException {
        String query = "INSERT INTO Movie (MovieId, GenreId) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, movieId);
            preparedStatement.setInt(2, 2); // Replace DEFAULT_GENRE_ID with the default genre ID
            preparedStatement.executeUpdate();
        }
    }
}
