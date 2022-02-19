package com.itmo.utils;

public class InterestRates {
    private double minSum;
    private double maxSum;
    private double rate;

    public InterestRates(double minSum, double maxSum, double rate) {
        this.minSum = minSum;
        this.maxSum = maxSum;
        this.rate = rate;
    }

    public double getMinSum() {
        return minSum;
    }

    public double getMaxSum() {
        return maxSum;
    }

    public double getRate() {
        return rate;
    }
}
