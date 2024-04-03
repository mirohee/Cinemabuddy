package controller;

import api.TvApi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Show;
import model.ShowParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeController {

    @FXML
    private Label showInfoTextArea;

    @FXML
    private VBox resultCardsContainer;

    @FXML
    private TextField searchField;

    @FXML
    private Button searchButton;

    @FXML
    private void initialize() {
        // You can perform any initialization tasks here
    }

    @FXML
    private void SearchButtonClicked(ActionEvent event) throws IOException {
        // Get the search query from the text field
        String searchQuery = searchField.getText();

        // Make API call to search for shows
        String response = TvApi.searchSingleShow(searchQuery);

        // Parse the response
        ShowParser showParser = new ShowParser();
        Show show = showParser.parseJsonResponse(response);

        // Display the show information on the search results page
        showInfoTextArea.setText("Name: " + show.getName() + "\n"
                + "Type: " + show.getType() + "\n"
                + "Genres: " + String.join(", ", show.getGenres()) + "\n"
                + "Status: " + show.getStatus() + "\n"
                + "Runtime: " + show.getRuntime() + " minutes\n"
                + "Premiered: " + show.getPremiered() + "\n"
                + "Rating: " + show.getRating().getAverage() + "\n"
                + "Summary: " + show.getSummary());

        // Create a list to hold the single show
        List<Show> shows = new ArrayList<>();
        shows.add(show);

        // Display search results
        displaySearchResults(shows);

        // Load the search results page after displaying search results
        Parent searchResultsParent = FXMLLoader.load(getClass().getResource("/DisplayResults.fxml"));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(searchResultsParent));
    }

    @FXML
    private void HistoryButtonClicked(ActionEvent event) throws IOException {
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/WatchHistory.fxml"));

        // Get the current stage
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

        // Set the scene with Register.fxml content
        stage.setScene(new Scene(homePageParent));
    }
    private void displaySearchResults(List<Show> shows) {
        resultCardsContainer.getChildren().clear(); // Clear previous results

        for (Show show : shows) {
            VBox resultCard = createResultCard(show);
            resultCardsContainer.getChildren().add(resultCard);
        }
    }

    private VBox createResultCard(Show show) {
        VBox resultCard = new VBox(10);
        resultCard.setStyle("-fx-background-color: #ffffff; -fx-padding: 10px;");

        Label titleLabel = new Label("Title: " + show.getName());
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #000000;");
        Label ratingLabel = new Label("Rating: " + show.getRating().getAverage());
        ratingLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #000000;");
        Label genreLabel = new Label("Genre: " + String.join(", ", show.getGenres()));
        genreLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #000000;");
        Label releaseYearLabel = new Label("Premiered: " + show.getPremiered());
        releaseYearLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #000000;");

        resultCard.getChildren().addAll(titleLabel, ratingLabel, genreLabel, releaseYearLabel);
        return resultCard;
    }
}
