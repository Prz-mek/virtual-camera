package com.example.virtualcamera;

import com.example.virtualcamera.controller.Controller;
import com.example.virtualcamera.model.map.Camera;
import com.example.virtualcamera.view.View;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Group root = new Group();
        Camera camera = new Camera();
        View view = new View(camera);
        Scene scene = new Scene(root);
        scene.setOnKeyPressed(new Controller(camera, view));
        root.getChildren().add(view);
        stage.setTitle("Virtual Camera");
        stage.setScene(scene);
        view.draw();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}