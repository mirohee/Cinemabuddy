package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Provides methods to interact with the TV Maze API.
 */
public class TvApi {
    private static final String BASE_URL = "https://api.tvmaze.com";

    /**
     * Makes an API call to the given endpoint and returns the response as a string.
     *
     * @param endpoint The API endpoint to call.
     * @return The response from the API as a string.
     * @throws IOException If an I/O error occurs while making the API call.
     */
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
            }
        } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
            throw new IOException("Show not found");
        } else {
            throw new IOException("Failed to fetch data from API. Response code: " + responseCode);
        }
    }

    /**
     * Searches for shows based on the provided search query.
     *
     * @param search The search query.
     * @return The response from the API as a string.
     * @throws IOException If an I/O error occurs while making the API call.
     */
    public static String searchShow(String search) throws IOException {
        String endpoint = BASE_URL + "/search/shows?q=" + search;
        return makeApiCall(endpoint);
    }

    /**
     * Searches for a single show based on the provided search query.
     *
     * @param search The search query.
     * @return The response from the API as a string.
     * @throws IOException If an I/O error occurs while making the API call.
     */
    public static String searchSingleShow(String search) throws IOException {
        String endpoint = BASE_URL + "/singlesearch/shows?q=" + search;
        return makeApiCall(endpoint);
    }

    /**
     * Searches for a show by its ID.
     *
     * @param id The ID of the show.
     * @return The response from the API as a string.
     * @throws IOException If an I/O error occurs while making the API call.
     */
    public static String searchShowByID(String id) throws IOException {
        String endpoint = BASE_URL + "/shows/" + id;
        return makeApiCall(endpoint);
    }

    /**
     * Searches for people based on the provided query.
     *
     * @param query The query to search for.
     * @return The response from the API as a string.
     * @throws IOException If an I/O error occurs while making the API call.
     */
    public static String peopleSearch(String query) throws IOException {
        String endpoint = BASE_URL + "/search/people?q=" + query;
        return makeApiCall(endpoint);
    }

    /**
     * Retrieves the episode list for a show by its ID.
     *
     * @param id             The ID of the show.
     * @param includeSpecials Whether to include special episodes in the list.
     * @return The response from the API as a string.
     * @throws IOException If an I/O error occurs while making the API call.
     */
    public static String getShowEpisodeList(String id, boolean includeSpecials) throws IOException {
        String endpoint = BASE_URL + "/shows/" + id + "/episodes";
        if (includeSpecials) {
            endpoint += "?specials=1";
        }
        return makeApiCall(endpoint);
    }
}
