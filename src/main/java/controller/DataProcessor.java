package controller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import model.TitleInfo;

public class DataProcessor {
    public static List<String> processedGenresResponse(String genresResponse) {
        List<String> genres = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(genresResponse);
            JSONArray jsonArray = jsonObject.getJSONArray("results");

            // Iterate over each genre in the array
            for (int i = 0; i < jsonArray.length(); i++) {
                String genre = jsonArray.getString(i);
                if (genre != null) {
                    genres.add(genre);
                    // Log the added genre
                    System.out.println("Added genre: " + genre);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            // Handle JSON parsing error
            System.err.println("Error processing genres response: " + e.getMessage());
        }

        return genres;
    }

    public static List<TitleInfo> processedSearchResults(String searchResults) {
        List<TitleInfo> titles = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(searchResults);
            if (jsonObject.has("results")) {
                JSONArray resultsArray = jsonObject.getJSONArray("results");

                for (int i = 0; i < resultsArray.length(); i++) {
                    JSONObject result = resultsArray.getJSONObject(i);

                    // Extract relevant information from each result object
                    String id = result.getString("id");
                    String title = result.getJSONObject("titleText").getString("text");
                    String imageUrl = result.getJSONObject("primaryImage").getString("url");

                    // Create a new TitleInfo object and add it to the list
                    TitleInfo titleInfo = new TitleInfo(id, title, imageUrl);
                    titles.add(titleInfo);

                    // Log the added title and ID
                    System.out.println("Added title: " + title + ", ID: " + id);
                }
            } else {
                // Handle missing "results" array
                System.err.println("Error: 'results' array not found in search results.");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            // Handle JSON parsing error
            System.err.println("Error processing search results: " + e.getMessage());
        }

        return titles;
    }
}
