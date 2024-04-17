package controller;

import api.TvApi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Show;
import model.ShowParser;
import util.LanguageManager;

import java.util.*;

import java.io.IOException;

public class HomeController {

    @FXML
    private Label showInfoTextArea;

    @FXML
    private VBox resultCardsContainer;

    @FXML
    private TextField searchField;

    @FXML
    private Label searchLabelText;

    @FXML
    private Button searchButton;
    @FXML
    private ComboBox<String> languageComboBox;

    private LanguageManager languageManager;

    @FXML
    public void initialize() {
        languageManager = LanguageManager.getInstance();
        languageManager.addLanguageSelector(languageComboBox);
        updateUIWithLocalizedText();
    }

    @FXML
    private void handleLanguageSelection(ActionEvent event) {
        languageManager.handleLanguageSelection(event);
        updateUIWithLocalizedText();
    }

    private void updateUIWithLocalizedText() {
        searchLabelText.setText(languageManager.getString("searchtext"));
        searchButton.setText(languageManager.getString("searchbutton"));
        searchField.setPromptText(languageManager.getString("searchinput"));
    }

    @FXML
    private void SearchButtonClicked(ActionEvent event) throws IOException {
        String searchQuery = searchField.getText();
        String response = TvApi.searchSingleShow(searchQuery);
        ShowParser showParser = new ShowParser();
        Show show = showParser.parseJsonResponse(response);

        showInfoTextArea.setText(languageManager.getString("title") + ": " + show.getName() + "\n"
                + languageManager.getString("type") + ": " + show.getType() + "\n"
                + languageManager.getString("genre") + ": " + String.join(", ", show.getGenres()) + "\n"
                + languageManager.getString("status") + ": " + show.getStatus() + "\n"
                + languageManager.getString("runtime") + ": " + show.getRuntime() + " minutes\n"
                + languageManager.getString("premiered") + ": " + show.getPremiered() + "\n"
                + languageManager.getString("rating") + ": " + show.getRating().getAverage() + "\n"
                + languageManager.getString("summary") + ": " + show.getSummary());

        List<Show> shows = new ArrayList<>();
        shows.add(show);
        displaySearchResults(shows);

        Parent searchResultsParent = FXMLLoader.load(getClass().getResource("/DisplayResults.fxml"));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(searchResultsParent));
    }

    @FXML
    private void HistoryButtonClicked(ActionEvent event) throws IOException {
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/WatchHistory.fxml"));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(homePageParent));
    }

    private void displaySearchResults(List<Show> shows) {
        resultCardsContainer.getChildren().clear();

        for (Show show : shows) {
            VBox resultCard = createResultCard(show);
            resultCardsContainer.getChildren().add(resultCard);
        }
    }

    private VBox createResultCard(Show show) {
        VBox resultCard = new VBox(10);
        resultCard.setStyle("-fx-background-color: #ffffff; -fx-padding: 10px;");

        Label titleLabel = new Label(languageManager.getString("title") + ": " + show.getName());
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #000000;");
        Label ratingLabel = new Label(languageManager.getString("rating") + ": " + show.getRating().getAverage());
        ratingLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #000000;");
        Label genreLabel = new Label(languageManager.getString("genre") + ": " + String.join(", ", show.getGenres()));
        genreLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #000000;");
        Label releaseYearLabel = new Label(languageManager.getString("premiered") + ": " + show.getPremiered());
        releaseYearLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #000000;");

        resultCard.getChildren().addAll(titleLabel, ratingLabel, genreLabel, releaseYearLabel);
        return resultCard;
    }
}