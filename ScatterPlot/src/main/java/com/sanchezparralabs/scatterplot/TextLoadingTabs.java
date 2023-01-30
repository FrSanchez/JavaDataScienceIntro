package com.sanchezparralabs.scatterplot;

import java.util.List;
import java.util.stream.Collectors;

public class TextLoadingTabs {
    public static void main(String[] args) {
        List<String> lines = TextLoader.getLines("auto-mpg.data");
        List<CarRecord> cars = lines.stream()
                .map(CarRecordUtil::parseRecord)
                .collect(Collectors.toList());

        cars.forEach(System.out::println);
    }
}
