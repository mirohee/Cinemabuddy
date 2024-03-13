package api;

import java.io.IOException;

public class TvApiTest {
    public static void main(String[] args) {
        testApiCall();
    }

    public static void testApiCall() {
        // Create an instance of ApiClient
        TvApi tvApi = new TvApi();

        try {
            String show = tvApi.searchShow("girls");
            System.out.println("show: " + show);

            // Test searchSingleShow method
            String searchSingleShowResult = tvApi.searchSingleShow("Girls");
            System.out.println("searchSingleShow Result: " + searchSingleShowResult);

            // Test searchShowByID method
            String searchShowByIDResult = tvApi.searchShowByID("139");
            System.out.println("searchShowByID Result: " + searchShowByIDResult);

            // Test peopleSearch method
            String peopleSearchResult = tvApi.peopleSearch("lauren");
            System.out.println("peopleSearch Result: " + peopleSearchResult);

            // Test getSchedule method
            String getScheduleResult = tvApi.getSchedule("US", "2022-01-01");
            System.out.println("getSchedule Result: " + getScheduleResult);

            // Test getWebStreamingSchedule method
            String getWebStreamingScheduleResult = tvApi.getWebStreamingSchedule("US", "2022-01-01");
            System.out.println("getWebStreamingSchedule Result: " + getWebStreamingScheduleResult);

            // Test getFullSchedule method
            String getFullScheduleResult = tvApi.getFullSchedule();
            System.out.println("getFullSchedule Result: " + getFullScheduleResult);

            // Test getShowMainInfo method
            String getShowMainInfoResult = tvApi.getShowMainInfo("1"); // Replace "1" with an actual show ID
            System.out.println("getShowMainInfo Result: " + getShowMainInfoResult);

            // Test getShowEpisodeList method
            String getShowEpisodeListResult = tvApi.getShowEpisodeList("1", true); // Replace "1" with an actual show ID
            System.out.println("getShowEpisodeList Result: " + getShowEpisodeListResult);
            

        } catch (IOException e) {
            e.printStackTrace();
            // Handle API call failure
            System.out.println("Failed to fetch data from the API");
        }
    }
}