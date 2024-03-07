package controller;

import java.util.ArrayList;
import java.util.List;

public class ContentBasedRecommenderTest {

    public static void main(String[] args) {
        // Sample genres
        List<String> selectedGenres = new ArrayList<>();
        selectedGenres.add("Action");
        selectedGenres.add("Adventure");
        selectedGenres.add("Comedy");

        // Create an instance of ContentBasedRecommender
        ContentBasedRecommender recommender = new ContentBasedRecommender();

        // Get movie recommendations based on the selected genres
        List<String> recommendations = recommender.generateRecommendations(selectedGenres);

        // Print the recommendations
        System.out.println("Recommended movies:");
        for (String movie : recommendations) {
            System.out.println(movie);
        }
    }
}
