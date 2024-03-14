package service;

import java.util.List;

public class ShowClusterUtilTest {
    public static void main(String[] args) {
        // Test a specific show
        String filePath = "data/cluster_position.csv";
        ShowClusterUtil.calculateShowClusterPositionsAndSaveToFile(filePath);
    }
}

