package com.example.virtualcamera.controller;

import com.example.virtualcamera.model.map.Camera;
import com.example.virtualcamera.model.map.Operations;
import com.example.virtualcamera.view.View;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;


public class Controller implements EventHandler<KeyEvent> {

    private final Camera camera;
    private final View view;

    public Controller(Camera camera, View view) {
        this.camera = camera;
        this.view = view;
    }


    @Override
    public void handle(KeyEvent event) {
        switch (event.getCode()) {
            case W:
                camera.transform(Operations.MOVE_FORWARD);
                break;
            case S:
                camera.transform(Operations.MOVE_BACKWARD);
                break;
            case A:
                camera.transform(Operations.MOVE_LEFT);
                break;
            case D:
                camera.transform(Operations.MOVE_RIGHT);
                break;
            case SPACE:
                camera.transform(Operations.MOVE_UP);
                break;
            case C:
                camera.transform(Operations.MOVE_DOWN);
                break;
            case J:
                camera.transform(Operations.ROTATE_LEFT);
                break;
            case L:
                camera.transform(Operations.ROTATE_RIGHT);
                break;
            case I:
                camera.transform(Operations.ROTATE_UP);
                break;
            case K:
                camera.transform(Operations.ROTATE_DOWN);
                break;
            case O:
                camera.transform(Operations.ROTATE_COUNTERCLOCKWISE);
                break;
            case U:
                camera.transform(Operations.ROTATE_CLOCKWISE);
                break;
            case X:
                camera.transform(Operations.ZOOM_OUT);
                break;
            case Z:
                camera.transform(Operations.ZOOM_IN);
                break;
            case ESCAPE:
                camera.reset();
                break;
        }

        view.draw();
    }
}