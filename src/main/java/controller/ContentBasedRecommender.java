package controller;

import api.ApiClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContentBasedRecommender {
    // Method to generate content-based recommendation
    public List<String> generateRecommendations(List<String> userPreferences) {
        List<String> recommendations = new ArrayList<>();

        try {
            // Fetch genres from API
            String genresResponse = ApiClient.getGenres();
            List<String> genres = DataProcessor.processedGenresResponse(genresResponse);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return recommendations;
    }
}