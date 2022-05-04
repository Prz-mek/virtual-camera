module com.example.virtualcamera {
    requires javafx.controls;
    requires javafx.fxml;
    requires commons.math3;


    opens com.example.virtualcamera to javafx.fxml;
    exports com.example.virtualcamera;
    exports com.example.virtualcamera.controller;
    opens com.example.virtualcamera.controller to javafx.fxml;
}