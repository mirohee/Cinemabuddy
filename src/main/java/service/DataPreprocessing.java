package service;

import model.Show;
import model.ShowParser;
import api.TvApi;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DataPreprocessing {
    public static void main(String[] args) throws IOException {
        // fetch tv show data from api
        String jsonResponse = TvApi.searchShow("game of thrones"); // example for now
        //List<Show> shows = parseJsonResponse(jsonResponse)
    }
}
