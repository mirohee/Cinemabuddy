package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

public class ApiClient {
    private static final String BASE_URL = "https://moviesdatabase.p.rapidapi.com";
    private static final String CONFIG_FILE = "config.properties";
    private static final String API_HOST = "moviesdatabase.p.rapidapi.com";
    private static final String API_KEY = loadApiKey();

    // Gets Api Key from config file
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

    // Method that makes api calls so it can be reused
    private static String makeApiCall(String endpoint) throws IOException {
        URL url = new URL(endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // X-RapidApi-Key header
        connection.setRequestProperty("X-RapidAPI-Key", API_KEY);
        // X-RapidApi-Host header
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
            throw new IOException("Failed to fetch data from API. Response code: " + responseCode);
        }
    }

    public static String getTitleByID(String id) throws IOException {
        String endpoint = BASE_URL + "/titles/" + id;
        return makeApiCall(endpoint);
    }

    public static String searchByAka(String aka) throws IOException {
        String endpoint = BASE_URL + "/titles/search/akas/" + aka;
        return makeApiCall(endpoint);
    }

    public static String getRatingsByID(String id) throws IOException {
        String endpoint = BASE_URL + "/titles/" + id + "/ratings";
        return makeApiCall(endpoint);
    }
    
}
