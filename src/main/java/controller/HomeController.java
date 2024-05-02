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

import java.io.IOException;
import java.util.*;

/**
 * Controller class for the home view of the Cinemabuddy application.
 */
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

    /**
     * Initializes the controller. Sets up the language manager and updates UI with localized text.
     */
    @FXML
    public void initialize() {
        languageManager = LanguageManager.getInstance();
        languageManager.addLanguageSelector(languageComboBox);
        updateUIWithLocalizedText();
    }

    /**
     * Handles language selection from the combo box.
     * @param event The ActionEvent triggered by language selection.
     */
    @FXML
    private void handleLanguageSelection(ActionEvent event) {
        languageManager.handleLanguageSelection(event);
        updateUIWithLocalizedText();
    }

    /**
     * Updates UI elements with localized text.
     */
    private void updateUIWithLocalizedText() {
        searchLabelText.setText(languageManager.getString("searchtext"));
        searchButton.setText(languageManager.getString("searchbutton"));
        searchField.setPromptText(languageManager.getString("searchinput"));
    }

    /**
     * Handles the event when the search button is clicked.
     * @param event The ActionEvent triggered by clicking the search button.
     * @throws IOException If an I/O error occurs while loading the search results.
     */
    @FXML
    private void SearchButtonClicked(ActionEvent event) throws IOException {
        String searchQuery = searchField.getText();
        String response = TvApi.searchSingleShow(searchQuery);
        ShowParser showParser = new ShowParser();
        Show show = showParser.parseJsonResponse(response);

        // Display show information in the text area
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

        // Load the search results in a new scene
        Parent searchResultsParent = FXMLLoader.load(getClass().getResource("/DisplayResults.fxml"));
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(searchResultsParent));
    }

    /**
     * Displays search results on the UI.
     * @param shows List of shows to display.
     */
    private void displaySearchResults(List<Show> shows) {
        resultCardsContainer.getChildren().clear();

        for (Show show : shows) {
            VBox resultCard = createResultCard(show);
            resultCardsContainer.getChildren().add(resultCard);
        }
    }

    /**
     * Creates a result card for a show.
     * @param show The show for which the result card is created.
     * @return The VBox representing the result card.
     */
    private VBox createResultCard(Show show) {
        VBox resultCard = new VBox(10);
        resultCard.setStyle("-fx-background-color: #ffffff; -fx-padding: 10px;");
        String defaultStyle = "-fx-font-size: 14px; -fx-text-fill: #000000;";

        Label titleLabel = new Label(languageManager.getString("title") + ": " + show.getName());
        titleLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #000000;");
        Label ratingLabel = new Label(languageManager.getString("rating") + ": " + show.getRating().getAverage());
        ratingLabel.setStyle(defaultStyle);
        Label genreLabel = new Label(languageManager.getString("genre") + ": " + String.join(", ", show.getGenres()));
        genreLabel.setStyle(defaultStyle);
        Label releaseYearLabel = new Label(languageManager.getString("premiered") + ": " + show.getPremiered());
        releaseYearLabel.setStyle(defaultStyle);

        resultCard.getChildren().addAll(titleLabel, ratingLabel, genreLabel, releaseYearLabel);
        return resultCard;
    }
}
