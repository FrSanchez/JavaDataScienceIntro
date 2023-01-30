package com.sanchezparralabs.scatterplot;

import org.apache.commons.csv.CSVRecord;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CSVLoading {
    public static void main(String[] args) {
        List<CSVRecord> records = CSVLoader.parseCSV("Groceries_dataset.csv");
        List<GroceryRecord> groceryRecords = records.stream()
                .map(GroceryRecordUtil::parseGroceryRecord)
                .collect(Collectors.toList());
        groceryRecords.forEach(System.out::println);

        Map<String, List<GroceryRecord>> recordsByMemberNumber =
                GroceryRecordUtil.groupGroceryRecordsByMemberNumber(groceryRecords);

        System.out.println(recordsByMemberNumber);
    }
}
