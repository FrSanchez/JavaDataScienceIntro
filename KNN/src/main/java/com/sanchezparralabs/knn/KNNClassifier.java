package com.sanchezparralabs.knn;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class KNNClassifier {
    List<DataItem> dataItems;

    public KNNClassifier() {
        this.dataItems = new ArrayList<>();
    }

    public void addDataItem(DataItem item) {
        dataItems.add(item);
    }

    public Map<String, Long> classifyPoint(DataItem unlabeledPoint, Integer numberOfNeighbors) {
        return dataItems.stream()
                .map(point -> new Pair<String, Double>(point.getClassifier(), DataItemUtil.getDistance(point, unlabeledPoint)))
                .sorted((d1, d2) -> d1.getValue().compareTo(d2.getValue()))
                .limit(numberOfNeighbors)
                .map(Pair::getKey)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
