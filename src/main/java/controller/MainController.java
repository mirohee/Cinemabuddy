package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.App;
import api.TvApi;
import model.Show;
import model.ShowParser;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import util.LanguageManager;

public class MainController {

    @FXML
    private TextField searchField;

    @FXML
    private Label showInfoTextArea;

    @FXML
    private VBox resultCardsContainer;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    private LanguageManager languageManager;
    @FXML
    public void initialize() {
        languageManager = new LanguageManager();
        languageManager.initialize();
        System.out.println("MainController initialized");

        localizeUI();
    }
    private void localizeUI() {
        registerButton.setText(languageManager.getString("register"));
        loginButton.setText(languageManager.getString("login"));
    }


    @FXML
    private void LoginButtonClicked(ActionEvent event) throws IOException {
        // Load the Homepage.fxml file
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/Login.fxml"));

        // Get the current stage
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

        // Set the scene with Homepage.fxml content
        stage.setScene(new Scene(homePageParent));
    }

    @FXML
    private void RegisterButtonClicked(ActionEvent event) throws IOException {
        // Load the Register.fxml file
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/Register.fxml"));

        // Get the current stage
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

        // Set the scene with Register.fxml content
        stage.setScene(new Scene(homePageParent));
    }
    @FXML
    private void HomepageButtonClicked(ActionEvent event) throws IOException {
        // Load the Register.fxml file
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/HomePage.fxml"));

        // Get the current stage
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

        // Set the scene with Register.fxml content
        stage.setScene(new Scene(homePageParent));
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



    public static void main (String[]args){
        App.launch(App.class);
    }
}



