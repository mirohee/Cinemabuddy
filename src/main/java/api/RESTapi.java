package api;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Version;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Scanner; // Import the Scanner class

public class RESTapi {

    private final HttpClient httpClient;

    public RESTapi() {
        this.httpClient = HttpClient.newBuilder()
                .version(Version.HTTP_2)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(20))
                .build();
    }

    public String getRecommendations(int showId, int numRecommendations) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:5000/recommend?show_id=" + showId + "&num_recommendations=" + numRecommendations))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public String searchAndRecommendShows(String showName, int numRecommendations) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("http://localhost:5000/search_and_recommend?name=" + URLEncoder.encode(showName, StandardCharsets.UTF_8) + "&num_recommendations=" + numRecommendations))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public static void main(String[] args) throws Exception {
        RESTapi apiClient = new RESTapi();
        Scanner scanner = new Scanner(System.in); // Create a Scanner object

        // Ask for the show name
        System.out.println("Enter show name:");
        String showName = scanner.nextLine(); // Read user input

        // Ask for the number of recommendations
        System.out.println("Enter number of recommendations:");
        int numRecommendations = scanner.nextInt(); // Read user input

        // Fetch and display the search results
        String searchResult = apiClient.searchAndRecommendShows(showName, numRecommendations);
        System.out.println(searchResult);

        scanner.close(); // Close the scanner
    }
}
