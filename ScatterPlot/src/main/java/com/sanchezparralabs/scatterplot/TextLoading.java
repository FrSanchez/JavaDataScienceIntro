package com.sanchezparralabs.scatterplot;

import java.util.*;
import java.util.stream.Collectors;

public class TextLoading {
    public static void main(String[] args) {
        List<String> lines = TextLoader.getLines("adult-sample.data");
        List<PersonRecord> people = lines.stream()
                .map(line -> PersonRecordUtil.parsePerson(line))
                .collect(Collectors.toList());

        people.forEach(System.out::println);
    }
}
