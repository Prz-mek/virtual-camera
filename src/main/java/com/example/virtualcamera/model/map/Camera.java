package com.example.virtualcamera.model.map;

import com.example.virtualcamera.model.transformations.ProjectionMatrix;
import com.example.virtualcamera.model.transformations.PositionMatrix;
import javafx.scene.canvas.GraphicsContext;

public class Camera {
    private PositionMatrix positionMatrix;
    private ProjectionMatrix projectionMatrix;


    public Camera() {
        reset();
    }

    public void transform(Operations operation) {
        switch (operation) {
            case MOVE_FORWARD:
                positionMatrix.translate(0, 0, -PositionMatrix.DEFAULT_DISTANCE);
                break;
            case MOVE_BACKWARD:
                positionMatrix.translate(0, 0, PositionMatrix.DEFAULT_DISTANCE);
                break;
            case MOVE_DOWN:
                positionMatrix.translate(0, -PositionMatrix.DEFAULT_DISTANCE, 0);
                break;
            case MOVE_LEFT:
                positionMatrix.translate(PositionMatrix.DEFAULT_DISTANCE, 0, 0);
                break;
            case MOVE_RIGHT:
                positionMatrix.translate(-PositionMatrix.DEFAULT_DISTANCE, 0, 0);
                break;
            case MOVE_UP:
                positionMatrix.translate(0, PositionMatrix.DEFAULT_DISTANCE, 0);
                break;
            case ROTATE_LEFT:
                positionMatrix.rotateY(PositionMatrix.DEFAULT_ANGLE);
                break;
            case ROTATE_RIGHT:
                positionMatrix.rotateY(-PositionMatrix.DEFAULT_ANGLE);
                break;
            case ROTATE_DOWN:
                positionMatrix.rotateX(PositionMatrix.DEFAULT_ANGLE);
                break;
            case ROTATE_UP:
                positionMatrix.rotateX(-PositionMatrix.DEFAULT_ANGLE);
                break;
            case ROTATE_CLOCKWISE:
                positionMatrix.rotateZ(PositionMatrix.DEFAULT_ANGLE);
                break;
            case ROTATE_COUNTERCLOCKWISE:
                positionMatrix.rotateZ(-PositionMatrix.DEFAULT_ANGLE);
                break;
            case ZOOM_IN:
                projectionMatrix.zoomIn();
                break;
            case ZOOM_OUT:
                projectionMatrix.zoomOut();
                break;
            default:
                break;
        }
    }

    public void draw(GraphicsContext gc) {
        for (Cuboid cuboid : Scene.DEFAULT_VIEW) {
            cuboid.copy().transform(positionMatrix).draw(gc, projectionMatrix);
        }
    }

    public void reset() {
        positionMatrix = new PositionMatrix();
        projectionMatrix = new ProjectionMatrix();
    }
}
