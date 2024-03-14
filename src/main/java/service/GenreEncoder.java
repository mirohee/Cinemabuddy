package service;

import java.util.HashMap;
import java.util.Map;

public class GenreEncoder {
    private static final Map<String, Integer> genreMap = new HashMap<>();

    static {
        // Add genres and corresponding numeric values to the map
        genreMap.put("Action", 0);
        genreMap.put("Adult", 1);
        genreMap.put("Adventure", 2);
        genreMap.put("Anime", 3);
        genreMap.put("Children", 4);
        genreMap.put("Comedy", 5);
        genreMap.put("Crime", 6);
        genreMap.put("DIY", 7);
        genreMap.put("Drama", 8);
        genreMap.put("Espionage", 9);
        genreMap.put("Family", 10);
        genreMap.put("Fantasy", 11);
        genreMap.put("Food", 12);
        genreMap.put("Horror", 13);
        genreMap.put("Legal", 14);
        genreMap.put("Medical", 15);
        genreMap.put("Music", 16);
        genreMap.put("Mystery", 17);
        genreMap.put("Nature", 18);
        genreMap.put("Romance", 19);
        genreMap.put("Science-Fiction", 20);
        genreMap.put("Sports", 21);
        genreMap.put("Supernatural", 22);
        genreMap.put("Thriller", 23);
        genreMap.put("Travel", 24);
        genreMap.put("War", 25);
        genreMap.put("Western", 26);
    }

    // Method to encode genre as numeric value
    public static int encodeGenre(String genre) {
        return genreMap.getOrDefault(genre, -1); // Return -1 if genre is not found
    }
}
