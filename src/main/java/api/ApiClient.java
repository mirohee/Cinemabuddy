package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

public class ApiClient {
    private static final String API_URL = "https://moviesdatabase.p.rapidapi.com/titles";
    private static final String CONFIG_FILE = "config.properties";
    private static final String API_HOST = "moviesdatabase.p.rapidapi.com";
    private static final String API_KEY = loadApiKey();

    private static String loadApiKey() {
        try {
            Properties properties = new Properties();
            properties.load(ApiClient.class.getClassLoader().getResourceAsStream(CONFIG_FILE));
            return properties.getProperty("API_KEY");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String searchMovieDataByTitle(String title) throws IOException {
        String encodedTitle = java.net.URLEncoder.encode(title, "UTF-8");
        String endpoint = API_URL + "?query=" + encodedTitle;

        URL url = new URL(endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        // Set X-RapidAPI-Key header
        connection.setRequestProperty("X-RapidAPI-Key", API_KEY);
        // Set X-RapidAPI-Host header
        connection.setRequestProperty("X-RapidAPI-Host", API_HOST);

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                return response.toString();
            }
        } else {
            throw new IOException("Failed to search for movie data. Response code: " + responseCode);
        }
    }
}
