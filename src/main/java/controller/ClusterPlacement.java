package controller;

import model.Show;
import service.GenreEncoder;

import weka.clusterers.Clusterer;
import weka.clusterers.SimpleKMeans;
import weka.core.*;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Normalize;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class ClusterPlacement {
    // method to calculate the position for each show based on the features
    public static double calculateClusterPosition(List<Show> shows) throws Exception {
        // Converting shows to instances
        Instances instances = convertToInstances(shows);

        // normalize data
        instances = normalizeData(instances);

        // perform the cluster
        Clusterer clusterer = new SimpleKMeans();
        clusterer.buildClusterer(instances);

        // Get cluster centers
        Instances centroids = ((SimpleKMeans) clusterer).getClusterCentroids();

        // Calculate the centroid of the cluster centers
        double[] clusterCenter = centroids.get(0).toDoubleArray();

        // Get all genres
        List<String> allGenres = getAllGenres(shows);

        return calculatePosition(clusterCenter, shows, allGenres);
    }

    private static Instances convertToInstances(List<Show> shows) {
        ArrayList<Attribute> attributes = new ArrayList<>();
        attributes.add(new Attribute("Rating"));
        // Add attributes for each genre
        for (String genre : getAllGenres(shows)) {
            attributes.add(new Attribute(genre));
        }
        Instances instances = new Instances("Shows", attributes, shows.size());

        // add instances (shows) to the dataset
        for (Show show : shows) {
            double[] values = new double[attributes.size()];
            values[0] = show.getRating().getAverage();
            // Initialize values for genres as 0
            for (int i = 1; i < values.length; i++) {
                values[i] = 0; // Initialize all genre values as 0
            }
            // Encode genres as numeric values
            for (String genre : show.getGenres()) {
                int index = attributes.indexOf(new Attribute(genre));
                values[index] = 1; // Assume presence of genre, you can refine this logic as needed
            }

            instances.add(new DenseInstance(1.0, values));
        }
        return instances;
    }

    private static List<String> getAllGenres(List<Show> shows) {
        Set<String> allGenres = new HashSet<>();
        for (Show show : shows) {
            allGenres.addAll(show.getGenres());
        }
        return new ArrayList<>(allGenres);
    }
    
    // method to normalize data
    private static Instances normalizeData(Instances instances) throws Exception {
        Normalize normalizeFilter = new Normalize();
        normalizeFilter.setInputFormat(instances);
        return Filter.useFilter(instances, normalizeFilter);
    }
    // method to calculate position based on cluster center
    private static double calculatePosition(double[] clusterCenter, List<Show> shows, List<String> allGenres) {
        double sumOfSquaredDistances = 0.0;
        for (Show show : shows) {
            double[] features = new double[1 + allGenres.size()]; // Rating + genres
            features[0] = show.getRating().getAverage();

            // Set genre values
            for (String genre : show.getGenres()) {
                int index = allGenres.indexOf(genre) + 1; // Adding 1 to skip the first index (rating)
                features[index] = 1; // Assume presence of genre, adjust logic as needed
            }

            double squaredDistance = calculateSquaredDistance(features, clusterCenter);
            sumOfSquaredDistances += squaredDistance;
        }
        return Math.sqrt(sumOfSquaredDistances);
    }

    private static double calculateSquaredDistance(double[] point1, double[] point2) {
        double sum = 0.0;
        for (int i = 0; i < point1.length; i++) {
            double diff = point1[i] - point2[i];
            sum += diff * diff;
        }
        return sum;
    }

}
