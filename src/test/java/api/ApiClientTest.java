package api;

import java.io.IOException;
public class ApiClientTest {
    public static void main(String[] args) {
        testApiCall();
    }

    public static void testApiCall() {
        // Create an instance of ApiClient
        ApiClient apiClient = new ApiClient();

        try {
            // Fetch details of a specific title by its IMDb ID
            String titleDetails = apiClient.getTitleByID("tt0000081");
            System.out.println("Title Details: " + titleDetails);

            // Search for titles based on a keyword
            String searchResults = apiClient.searchByAka("Breaking Bad");
            System.out.println("Search Results: " + searchResults);

            // Search for ratings based on title
            String rating = apiClient.getRatingsByID("tt0000081");
            System.out.println("Rating: " + rating);

            //Fetch array of genres
            String genres = apiClient.getGenres();
            System.out.println("Genres: " + genres);

            // Get actor by id
            String actor = apiClient.getActorsByID("nm6966606");
            System.out.println("Actor: " + actor);

        } catch (IOException e) {
            e.printStackTrace();
            // Handle API call failure
            System.out.println("Failed to fetch data from the API");
        }
    }
}
