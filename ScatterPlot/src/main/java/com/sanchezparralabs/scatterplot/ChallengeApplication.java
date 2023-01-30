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
import java.util.stream.Collectors;

public class ChallengeApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    String[] originNames = {"American", "European", "Asian"};

    @Override
    public void start(Stage stage) throws Exception {
        List<String> lines = TextLoader.getLines("auto-mpg.data");
        List<CarRecord> cars = lines.stream()
                .map(CarRecordUtil::parseRecord)
                .toList();

        Map<Integer, List<CarRecord>> carsByOrigin =  cars.stream()
                .collect(Collectors.groupingBy(CarRecordUtil::getOrigin));

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        for(Integer origin : carsByOrigin.keySet()) {
            series.getData().add(
                    new XYChart.Data<String, Number>(
                            originNames[origin -1],
                            CarRecordUtil.getAverageMpg(carsByOrigin.get(origin))));
        }

        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Origin");
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
