package api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import model.Show;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class TvApi {
    private static final String BASE_URL = "https://api.tvmaze.com";

    // Method that makes api calls so it can be reused
    public static String makeApiCall(String endpoint) throws IOException {
        URL url = new URL(endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                return response.toString();
            } } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
            throw new IOException("Show not found");
        } else {
            throw new IOException("Failed to fetch data from API. Response code: " + responseCode);
        }
    }

    public static String searchShow(String search) throws IOException {
        String endpoint = BASE_URL + "/search/shows?q=" + search;
        return makeApiCall(endpoint);
    }

    public static String searchSingleShow(String search) throws IOException{
        String endpoint = BASE_URL + "/singlesearch/shows?q=" + search;
        return makeApiCall(endpoint);
    }

    public static String searchShowByID(String id) throws IOException{
        String endpoint = BASE_URL + "/shows/" + id;
        return makeApiCall(endpoint);
    }

    public static String peopleSearch(String query) throws IOException {
        String endpoint = BASE_URL + "/search/people?q=" + query;
        return makeApiCall(endpoint);
    }

    public static String getShowEpisodeList(String id, boolean includeSpecials) throws IOException {
        String endpoint = BASE_URL + "/shows/" + id + "/episodes";
        if (includeSpecials) {
            endpoint += "?specials=1";
        }
        return makeApiCall(endpoint);
    }
}
