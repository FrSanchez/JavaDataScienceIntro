package com.sanchezparralabs.scatterplot;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class ScatterPlotApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        List<String> lines = TextLoader.getLines("auto-mpg.data");
        List<CarRecord> cars = lines.stream()
                .map(CarRecordUtil::parseRecord)
                .toList();

        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Weight");
        yAxis.setLabel("MPG");

        ScatterChart<Number, Number> scatterChart = new ScatterChart<>(xAxis, yAxis);
        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();

        series1.setName("Up to 76");
        series2.setName("Above 76");;

        cars.forEach(car -> {
            XYChart.Data data = new XYChart.Data(car.weight, car.mpg);
            if (car.modelYear <= 76) {
                series1.getData().add(data);
            } else {
                series2.getData().add(data);
            }
        });

        scatterChart.getData().add(series1);
        scatterChart.getData().add(series2);

        scatterChart.setTitle("Weight vs Mileage");
        scatterChart.setHorizontalGridLinesVisible(false);
        scatterChart.setVerticalGridLinesVisible(false);
        scatterChart.setHorizontalZeroLineVisible(false);

        Scene scene = new Scene(scatterChart, 900, 1000);
        stage.setScene(scene);
        stage.setTitle("Scatter Plot");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}