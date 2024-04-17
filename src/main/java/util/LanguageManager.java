package util;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageManager {

    private static LanguageManager instance;
    private Locale locale;
    private ResourceBundle bundle;

    private LanguageManager() {
        // Set default language
        loadLanguage("en", "UK");
    }

    public static LanguageManager getInstance() {
        if (instance == null) {
            instance = new LanguageManager();
        }
        return instance;
    }

    public String getString(String key) {
        return bundle.getString(key);
    }

    public void loadLanguage(String lang, String country) {
        locale = new Locale(lang, country);
        bundle = ResourceBundle.getBundle("languages", locale);
    }

    public void changeLanguage(String lang, String country) {
        loadLanguage(lang, country);
        System.out.println("Language changed to " + lang + "_" + country);
    }

    public ResourceBundle getResourceBundle() {
        return bundle;
    }

    public Locale getLocale() {
        return locale;
    }

    public void addLanguageSelector(ComboBox<String> languageComboBox) {
        languageComboBox.getItems().addAll("English", "Persian", "Finnish"); // Add display names for languages
        String selectedLanguage = locale.getDisplayLanguage();
        languageComboBox.setValue(selectedLanguage); // Set default value
    }

    public void handleLanguageSelection(ActionEvent event) {
        ComboBox<String> comboBox = (ComboBox<String>) event.getSource();
        String selectedLanguage = comboBox.getValue();
        String langCode;
        String countryCode;

        // Map display names to language codes
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
                langCode = "en"; // Default to English
                countryCode = "UK";
                break;
        }

        // Change language
        changeLanguage(langCode, countryCode);
    }
}
