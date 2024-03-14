package service;

import java.util.List;

public class ShowClusterUtilTest {
    public static void main(String[] args) {
        // Test a specific show
        List clusterPosition = ShowClusterUtil.calculateShowClusterPositions();
        System.out.println("Cluster Position: " + clusterPosition);
    }
}

