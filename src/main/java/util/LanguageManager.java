package util;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Manages localization and language selection in the application.
 */
public class LanguageManager {

    private static LanguageManager instance;
    private Locale locale;
    private ResourceBundle bundle;

    /**
     * Constructs a LanguageManager object and sets the default language to English (UK).
     */
    private LanguageManager() {
        loadLanguage("en", "UK");
    }

    /**
     * Gets the instance of LanguageManager (Singleton pattern).
     *
     * @return The instance of LanguageManager.
     */
    public static LanguageManager getInstance() {
        if (instance == null) {
            instance = new LanguageManager();
        }
        return instance;
    }

    /**
     * Gets the localized string for the given key.
     *
     * @param key The key for the localized string.
     * @return The localized string.
     */
    public String getString(String key) {
        return bundle.getString(key);
    }

    /**
     * Loads the specified language and country.
     *
     * @param lang    The language code (e.g., "en").
     * @param country The country code (e.g., "UK").
     */
    public void loadLanguage(String lang, String country) {
        locale = new Locale(lang, country);
        bundle = ResourceBundle.getBundle("languages", locale);
    }

    /**
     * Changes the current language to the specified language and country.
     *
     * @param lang    The language code (e.g., "en").
     * @param country The country code (e.g., "UK").
     */
    public void changeLanguage(String lang, String country) {
        loadLanguage(lang, country);
    }

    /**
     * Gets the resource bundle.
     *
     * @return The resource bundle.
     */
    public ResourceBundle getResourceBundle() {
        return bundle;
    }

    /**
     * Gets the current locale.
     *
     * @return The current locale.
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * Adds language options to the provided ComboBox.
     *
     * @param languageComboBox The ComboBox for language selection.
     */
    public void addLanguageSelector(ComboBox<String> languageComboBox) {
        languageComboBox.getItems().addAll("English", "Persian", "Finnish");
        String selectedLanguage = locale.getDisplayLanguage();
        languageComboBox.setValue(selectedLanguage);
    }

    /**
     * Handles language selection events triggered by the ComboBox.
     *
     * @param event The ActionEvent representing the language selection event.
     */
    public void handleLanguageSelection(ActionEvent event) {
        ComboBox<String> comboBox = (ComboBox<String>) event.getSource();
        String selectedLanguage = comboBox.getValue();
        String langCode;
        String countryCode;

        switch (selectedLanguage) {
            case "English":
                langCode = "en";
                countryCode = "UK";
                break;
            case "Persian":
                langCode = "fa";
                countryCode = "IR";
                break;
            case "Finnish":
                langCode = "fin";
                countryCode = "FI";
                break;
            default:
                langCode = "en";
                countryCode = "UK";
        }

        changeLanguage(langCode, countryCode);
    }
}
