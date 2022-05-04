package com.example.virtualcamera.view;

import com.example.virtualcamera.model.map.Camera;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class View extends Canvas {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private Camera camera;

    public View(Camera camera) {
        super(WIDTH, HEIGHT);
        this.camera = camera;
    }

    public void draw() {
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, WIDTH, HEIGHT);
        camera.draw(gc);
    }
}
