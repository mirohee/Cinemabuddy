package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ShowParserTest {

    @Test
    public void testParseJsonResponse() {
        String jsonResponse = "{\n" +
                "    \"id\": 139,\n" +
                "    \"name\": \"Girls\",\n" +
                "    \"type\": \"Scripted\",\n" +
                "    \"language\": \"English\",\n" +
                "    \"genres\": [\n" +
                "        \"Drama\",\n" +
                "        \"Romance\"\n" +
                "    ],\n" +
                "    \"status\": \"Ended\",\n" +
                "    \"runtime\": 30,\n" +
                "    \"averageRuntime\": 30,\n" +
                "    \"premiered\": \"2012-04-15\",\n" +
                "    \"ended\": \"2017-04-16\",\n" +
                "    \"officialSite\": \"http://www.hbo.com/girls\",\n" +
                "    \"rating\": {\n" +
                "        \"average\": 6.5\n" +
                "    },\n" +
                "    \"image\": {\n" +
                "        \"medium\": \"https://static.tvmaze.com/uploads/images/medium_portrait/31/78286.jpg\",\n" +
                "        \"original\": \"https://static.tvmaze.com/uploads/images/original_untouched/31/78286.jpg\"\n" +
                "    },\n" +
                "    \"summary\": \"<p>This Emmy winning series is a comic look at the assorted humiliations and rare triumphs of a group of girls in their 20s.</p>\"\n" +
                "}";

        ShowParser parser = new ShowParser();
        Show show = parser.parseJsonResponse(jsonResponse);

        assertNotNull(show);
        assertEquals(139, show.getId());
        assertEquals("Girls", show.getName());
        assertEquals("Scripted", show.getType());
        assertEquals("English", show.getLanguage());
        assertEquals(2, show.getGenres().size());
        assertEquals("Ended", show.getStatus());
        assertEquals(30, show.getRuntime());
        assertEquals(30, show.getAverageRuntime());
        assertEquals("2012-04-15", show.getPremiered());
        assertEquals("2017-04-16", show.getEnded());
        assertEquals("http://www.hbo.com/girls", show.getOfficialSite());
        assertEquals(6.5, show.getRating().getAverage());
        assertEquals("https://static.tvmaze.com/uploads/images/medium_portrait/31/78286.jpg", show.getImageUrl());
        assertEquals("<p>This Emmy winning series is a comic look at the assorted humiliations and rare triumphs of a group of girls in their 20s.</p>", show.getSummary());
    }
}
