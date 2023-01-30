module com.example.knn {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.csv;
    requires commons.math3;

    opens com.example.knn to javafx.fxml;
    exports com.sanchezparralabs.knn;
    opens com.sanchezparralabs.knn to javafx.fxml;
}