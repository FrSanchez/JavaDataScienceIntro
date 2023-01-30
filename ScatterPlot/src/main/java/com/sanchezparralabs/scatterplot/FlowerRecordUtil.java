package com.sanchezparralabs.scatterplot;

import org.apache.commons.csv.CSVRecord;

public class FlowerRecordUtil {
/*
   1. sepal length in cm
   2. sepal width in cm
   3. petal length in cm
   4. petal width in cm
   5. class:
 */
    public static FlowerRecord parseRecord(CSVRecord record) {
        try {
            Double sepalLength = Double.parseDouble( record.get(0));
            Double sepalWidth = Double.parseDouble(record.get(1));
            Double petalLength = Double.parseDouble( record.get(2));
            Double petalWidth = Double.parseDouble(record.get(3));
            String species = record.get(4);

            return new FlowerRecord(sepalLength, sepalWidth, petalLength, petalWidth, species);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

