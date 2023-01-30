package com.sanchezparralabs.knn;

import org.apache.commons.csv.CSVRecord;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class NaiveBayesExample {

    public static void main(String[] args) {
        List<CSVRecord> records = CSVLoader.parseCSV("iris.data");
        List<FlowerRecord> flowers = records.stream()
                .map(FlowerRecordUtil::parseRecord)
                .toList();

        NaiveBayesClassifier classifier = new NaiveBayesClassifier();
        flowers.forEach(classifier::addDataItem);

        Map<String, Double> classification = classifier.classifyPoint(new FlowerRecord(5.8, 5.8, 5.1, 2.4, "??"));
        System.out.println(classification);
    }
}
