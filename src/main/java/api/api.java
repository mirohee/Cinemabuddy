package api;

import java.io.BufferedReader;
import java.io.IOException;
import  java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class api {
    private static final String API_KEY = "193c769435msh06fb2b3f91439c6p1e631bjsn875412fd6535";
    private static final String API_URL = "https://api.moviesdatabase.com/v1/";

    // Method to fetch a movie's rating by its ID
    public String fetchMovieRatingById(String movieId) throws IOException {
        String endpoint = API_URL + "movies/" + movieId + "/ratings";

        URL url = new URL(endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else {
            // Handle error response
            return null;
        }
    }
}
