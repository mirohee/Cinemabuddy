package service;

import model.Show;
import api.TvApi;
import controller.ClusterPlacement;
import model.ShowParser;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.ArrayList;

public class ShowClusterUtil {
    private static final String[] SHOW_IDS = {"139", "140", "141"}; // Replace with the IDs of the shows

    public static List<Double> calculateShowClusterPositionsAndSaveToFile(String filePath) {
        List<Double> clusterPositions = new ArrayList<>();
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (String showId : SHOW_IDS) {
                // Step 1: Fetch data of a single TV show from the API
                String jsonResponse = TvApi.searchShowByID(showId);

                // Step 2: Parse JSON response and create a Show object
                Show show = parseJsonResponse(jsonResponse);

                // Step 3: Calculate cluster position for the show
                double clusterPosition = ClusterPlacement.calculateClusterPosition(List.of(show));
                clusterPositions.add(clusterPosition);

                // Write show id and cluster position to file
                writer.println(showId + "," + clusterPosition);
            }
            System.out.println("Cluster positions saved to file: " + filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clusterPositions;
    }

    // Method to parse JSON response and create a Show object
    private static Show parseJsonResponse(String jsonResponse) {
        ShowParser parser = new ShowParser();
        return parser.parseJsonResponse(jsonResponse);
    }
}