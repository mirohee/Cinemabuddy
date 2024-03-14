package model;

import weka.clusterers.ClusterEvaluation;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import java.sql.SQLOutput;

public class Clustering {
    public static void main(String[] args) throws Exception {
        // Load preprocessed data
        Instances data = DataSource.read("path_to_data.arff");

        // Initialize the K Means algorithm
        SimpleKMeans kMeans = new SimpleKMeans();
        kMeans.setNumClusters(3);

        // build clustering model
        kMeans.buildClusterer(data);

        // obtain cluster assignments for each instance
        int[] assignments = kMeans.getAssignments();

        // evaluate clustering
        ClusterEvaluation eval = new ClusterEvaluation();
        eval.setClusterer(kMeans);
        eval.evaluateClusterer(data);

        // print cluster assignment
        for(int i = 0; i < data.numInstances(); i++) {
            System.out.println("Instance " +  i  + " is assigned to cluster " + assignments[i]);
        }

        // print the cluster evalution
        System.out.println(eval.clusterResultsToString());
    }
}
