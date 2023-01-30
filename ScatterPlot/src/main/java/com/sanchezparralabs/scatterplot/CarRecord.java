package com.sanchezparralabs.scatterplot;

public class CarRecord {
    /*
        1. mpg:           continuous
    2. cylinders:     multi-valued discrete
    3. displacement:  continuous
    4. horsepower:    continuous
    5. weight:        continuous
    6. acceleration:  continuous
    7. model year:    multi-valued discrete
    8. origin:        multi-valued discrete
    9. car name:      string (unique for each instance)
     */
    public final Float mpg;
    public final Integer cylinders;
    public final Float displacement;
    public final Float weight;
    public final Integer modelYear;
    public final Integer origin;

    public CarRecord(Float mpg, Integer cylinders, Float displacement, Float weight, Integer modelYear, Integer origin) {
        this.mpg = mpg;
        this.cylinders = cylinders;
        this.displacement = displacement;
        this.weight = weight;
        this.modelYear = modelYear;
        this.origin = origin;
    }

    @Override
    public String toString() {
        return "CarRecord{" +
                "mpg=" + mpg +
                ", cylinders=" + cylinders +
                ", displacement=" + displacement +
                ", weight=" + weight +
                '}';
    }
}
