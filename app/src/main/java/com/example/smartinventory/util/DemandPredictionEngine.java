package com.example.smartinventory.util;

public class DemandPredictionEngine {

    /**
     * Predicts how many days stock will last
     */
    public static int predictDaysLeft(int currentStock, double avgDailySales) {
        if (avgDailySales <= 0) return Integer.MAX_VALUE;
        return (int) Math.ceil(currentStock / avgDailySales);
    }
}
