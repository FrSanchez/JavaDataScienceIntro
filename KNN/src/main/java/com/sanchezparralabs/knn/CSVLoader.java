package com.sanchezparralabs.knn;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class CSVLoader {
    public static List<CSVRecord> parseCSV(String filename) {
        List<CSVRecord> records =new ArrayList<>();
        try {
            Reader reader = new FileReader(filename);
            Iterable<CSVRecord> recordsIterable = CSVFormat.RFC4180.parse(reader);
            for(CSVRecord record : recordsIterable) {
                records.add(record);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return records;
    }
}
