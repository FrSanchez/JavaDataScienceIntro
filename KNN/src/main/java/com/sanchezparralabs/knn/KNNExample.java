package com.sanchezparralabs.knn;

import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KNNExample {
    public static void main(String[] args) {
        List<CSVRecord> records = CSVLoader.parseCSV("iris.data");
        List<FlowerRecord> flowers = records.stream()
                .map(FlowerRecordUtil::parseRecord)
                .collect(Collectors.toList());

        KNNClassifier classifier = new KNNClassifier();
        flowers.forEach(classifier::addDataItem);

        Map<String, Long> classification = classifier.classifyPoint(new FlowerRecord(1.8,1.8,5.1,2.4, "??"), 30);
        System.out.println(classification);
    }

}