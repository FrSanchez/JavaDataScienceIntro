package com.sanchezparralabs.knn;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.StatUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NaiveBayesClassifier {
    private List<DataItem> dataItems;

    public NaiveBayesClassifier() {
        dataItems = new ArrayList<>();
    }

    public void addDataItem(DataItem item) {
        dataItems.add(item);
    }



    public Map<String, Double> classifyPoint(DataItem unlabeledPoint) {
        Map<String, List<DataItem>> set = dataItems.stream()
                .collect(Collectors.groupingBy(point -> point.getClassifier()));

        Map<String, Double> probabilities = new HashMap<>();

        for(String label : set.keySet()) {
            // mean for every column of data
            List<Double> groupColumnMeans = getColumnMeans(set.get(label));
            List<Double> groupColumnStdDevs = getColumnStdDevs(set.get(label));

            probabilities.put(
                    label, getLabelProbabilityForPoint(groupColumnMeans, groupColumnStdDevs, unlabeledPoint)
            );
        }
        return probabilities;
    }

    private Double getLabelProbabilityForPoint(List<Double> groupColumnMeans, List<Double> groupColumnStdDevs, DataItem unlabeledPoint) {
        double probability = 1.0;
        for(int i = 0; i < groupColumnMeans.size(); i++) {
            Double mean = groupColumnMeans.get(i);
            Double stdDev = groupColumnStdDevs.get(i);
            Double itemValue = unlabeledPoint.getCoordinates().get(i);
            NormalDistribution dist = new NormalDistribution(mean, stdDev);
            probability *= dist.cumulativeProbability(itemValue);
        }
        return probability;
    }

    private List<Double> getColumnStdDevs(List<DataItem> items) {
        int size = items.get(0).getCoordinates().size();
        List<Double> columnStdDevs = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            int finalI = i;
            double columnStdDev = StatUtils.variance(
                    items.stream()
                            .map(item -> item.getCoordinates().get(finalI))
                            .mapToDouble(Double::doubleValue)
                            .toArray()
            );
            columnStdDevs.add(Math.sqrt(columnStdDev));
        }
        return columnStdDevs;
    }

    private List<Double> getColumnMeans(List<DataItem> items) {
        int size = items.get(0).getCoordinates().size();
        List<Double> columnMeans = new ArrayList<>(size);
        double[][] columns = new double[size][];
        for(int i = 0; i < size; i++) {
            columns[i] = new double[items.size()];
        }
        for(int r = 0; r < items.size(); r++) {
            for (int i = 0; i < size; i++) {
                columns[i][r] = items.get(r).getCoordinates().get(i);
            }
        }

        for(int i = 0; i < size; i++) {
            Double columnMean = StatUtils.mean(columns[i]);
            columnMeans.add(columnMean);
        }
        return columnMeans;
    }
}
