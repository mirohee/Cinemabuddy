package controller;

import api.ApiClient;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContentBasedRecommender {
    // Method to load and prepare dataset
    private Instances loadDataset(String datasetPath) {
        try {
            DataSource source = new DataSource(datasetPath);
            Instances data = source.getDataSet();
            if (data.classIndex() == -1)
                data.setClassIndex(data.numAttributes() - 1);
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to train a J48 model
    private J48 trainModel(Instances dataset) {
        try {
            J48 tree = new J48();
            tree.buildClassifier(dataset);
            return tree;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Method to generate content-based recommendation
    public List<String> generateRecommendations(List<String> userPreferences, String datasetPath) {
        List<String> recommendations = new ArrayList<>();

        try {
            // Load dataset
            Instances dataset = loadDataset(datasetPath);
            if (dataset == null) {
                System.out.println("Failed to load dataset.");
                return recommendations;
            }

            // Train model
            J48 model = trainModel(dataset);
            if (model == null) {
                System.out.println("Failed to train model.");
                return recommendations;
            }

            // Fetch genres from API
            String genresResponse = ApiClient.getGenres();
            List<String> genres = DataProcessor.processedGenresResponse(genresResponse);

            // Here you would use the trained model to make predictions
            // and generate recommendations based on user preferences and genres

        } catch (IOException e) {
            e.printStackTrace();
        }
        return recommendations;
    }
}
