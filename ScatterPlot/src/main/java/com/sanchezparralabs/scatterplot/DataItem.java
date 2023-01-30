package com.sanchezparralabs.scatterplot;

import java.util.List;

public interface DataItem {
    public List<Double> getCoordinates();
    public String getClassifier();
}
