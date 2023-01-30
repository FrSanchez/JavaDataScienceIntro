package com.sanchezparralabs.scatterplot;

import java.util.Arrays;
import java.util.List;

public class CarRecordUtil {
    public static CarRecord parseRecord(String line) {
        List<String> fields = Arrays.asList(line.split("\\s+"));
        try {
            Float mpg = Float.parseFloat(fields.get(0));
            Integer cylinders = Integer.parseInt(fields.get(1));
            Float displacement = Float.parseFloat(fields.get(2));
            Float weight = Float.parseFloat(fields.get(4));
            Integer modelYear = Integer.parseInt(fields.get(6));
            Integer origin = Integer.parseInt(fields.get(7));
            return new CarRecord(mpg, cylinders, displacement, weight, modelYear, origin);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Integer getNumberOfCylinders(CarRecord car) {
        return car.cylinders;
    }

    public static Float getAverageMpg(List<CarRecord> cars) {
        return (float) cars.stream().mapToDouble(car -> car.mpg).average().orElse(0);
    }

    public static Integer getOrigin(CarRecord car) {
        return car.origin;
    }
}
