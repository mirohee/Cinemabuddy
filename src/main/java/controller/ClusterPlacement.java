package controller;

import model.Show;

import weka.clusterers.Clusterer;
import weka.clusterers.SimpleKMeans;
import weka.core.*;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Normalize;

import java.util.ArrayList;
import java.util.List;


public class ClusterPlacement {
    // method to calculate the position for each show based on the features
    public static double calculateClusterPosition(List<Show> shows) throws Exception {
        // Converting shows to instaces
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

        return calculatePosition(clusterCenter, shows);

    }
    private static Instances convertToInstances(List<Show> shows) {
        ArrayList<Attribute> attributes = new ArrayList<>();
        // add more later
        attributes.add(new Attribute("Rating"));
        Instances instances = new Instances("Shows", attributes, shows.size());

        // add instances (shows) to the dataset
        for (Show show : shows) {
            double[] values = new double[1];
            values[0] = show.getRating().getAverage();
            instances.add(new DenseInstance(1.0, values));
        }
        return instances;
    }

    // method to normalize data
    private static Instances normalizeData(Instances instances) throws Exception {
        Normalize normalizeFilter = new Normalize();
        normalizeFilter.setInputFormat(instances);
        return Filter.useFilter(instances, normalizeFilter);
    }
    // method to calculate position based on cluster center
    private static double calculatePosition(double[] clusterCenter, List<Show> shows) {
        double sumOfSquaredDistances = 0.0;
        for (Show show : shows) {
            double[] features = new double[]{show.getRating().getAverage()}; // Assuming only rating is used for clustering
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
