package controller;

import model.TitleInfo;

import java.util.List;

public class DataProcessorTest {

    public static void main(String[] args) {
        testProcessedGenresResponse();
        testProcessedSearchResults();
    }

    public static void testProcessedGenresResponse() {
        // Sample genres response
        String genresResponse = "{\"results\":[\"Action\",\"Drama\",\"Comedy\"]}";

        // Test processedGenresResponse method
        List<String> genres = DataProcessor.processedGenresResponse(genresResponse);

        // Assert that the genres list is not null and contains expected genres
        assert genres != null;
        assert genres.size() == 3;
        assert genres.contains("Action");
        assert genres.contains("Drama");
        assert genres.contains("Comedy");

        System.out.println("Processed Genres Response Test Passed");
    }

    public static void testProcessedSearchResults() {
        // Sample search results
        String searchResults = "{\"results\":[{\"id\":\"123\",\"titleText\":{\"text\":\"Movie Title\"}," +
                "\"primaryImage\":{\"url\":\"http://example.com/image.jpg\"}}]}";

        // Test processedSearchResults method
        List<TitleInfo> titles = DataProcessor.processedSearchResults(searchResults);

        // Assert that the titles list is not null and contains expected title information
        assert titles != null;
        assert titles.size() == 1;
        TitleInfo titleInfo = titles.get(0);
        assert titleInfo.getId().equals("123");
        assert titleInfo.getTitle().equals("Movie Title");
        assert titleInfo.getPosterUrl().equals("http://example.com/image.jpg");

        System.out.println("Processed Search Results Test Passed");
    }
}
