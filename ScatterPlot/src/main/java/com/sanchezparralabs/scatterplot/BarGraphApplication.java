package com.sanchezparralabs.scatterplot;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class BarGraphApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        List<String> lines = TextLoader.getLines("auto-mpg.data");
        List<CarRecord> cars = lines.stream()
                .map(CarRecordUtil::parseRecord)
                .toList();

        Map<Integer, List<CarRecord>> carsByCylinders =  cars.stream()
                .collect(Collectors.groupingBy(CarRecordUtil::getNumberOfCylinders));

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for(Integer cylinders : carsByCylinders.keySet()) {
            series.getData().add(new XYChart.Data<String, Number>(cylinders.toString(), CarRecordUtil.getAverageMpg(carsByCylinders.get(cylinders))));
        }

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of cylinders");
        yAxis.setLabel("Average mpg");

        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setAnimated(true);
        barChart.getData().add(series);
        barChart.setTitle("Average mpg of different engine types");
        barChart.setHorizontalGridLinesVisible(false);
        barChart.setVerticalGridLinesVisible(false);
        barChart.setHorizontalZeroLineVisible(false);

        Scene scene = new Scene(barChart, 1000, 1000);
        stage.setScene(scene);
        stage.setTitle("Scatter Plot");
        stage.show();
    }
}
