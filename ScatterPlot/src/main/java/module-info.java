module com.sanchezparralabs.scatterplot {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.csv;


    opens com.sanchezparralabs.scatterplot to javafx.fxml;
    exports com.sanchezparralabs.scatterplot;
}