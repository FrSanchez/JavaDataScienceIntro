package com.sanchezparralabs.scatterplot;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import org.apache.commons.csv.CSVRecord;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class IrisPlotApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        List<CSVRecord> records = CSVLoader.parseCSV("iris.data");
        List<FlowerRecord> flowers = records.stream()
                .map(FlowerRecordUtil::parseRecord)
                .toList();
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Sepal Length");
        yAxis.setLabel("Sepal Width");

        ScatterChart<Number, Number> scatterChart = new ScatterChart<>(xAxis, yAxis);
        Map<String, XYChart.Series<Number, Number>> map = new HashMap<>();
        flowers.forEach(f->{
            if(!map.containsKey(f.species)) {
                XYChart.Series<Number, Number> series = new XYChart.Series<>();
                series.setName(f.species);
                map.put(f.species, series);
            }
            map.get(f.species).getData().add(new XYChart.Data<>(f.petalLength, f.petalWidth));
        });

        ScatterChart<Number,Number> chart = new ScatterChart<>(xAxis, yAxis);


        for(String species : map.keySet()) {
            chart.getData().add(map.get(species));
        }
        chart.setTitle("Average mpg of different engine types");
        chart.setHorizontalGridLinesVisible(false);
        chart.setVerticalGridLinesVisible(false);
        chart.setHorizontalZeroLineVisible(false);

        Scene scene = new Scene(chart, 1000, 1000);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Scatter Plot");
        primaryStage.show();
    }
}
