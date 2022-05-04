package com.example.virtualcamera.model.transformations;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;

public class ProjectionMatrix extends Array2DRowRealMatrix {
    private static final double VIEWPORT_DISTANCE = 500;
    private static final double DEFAULT_ZOOM = 1.2;

    public ProjectionMatrix() {
        super(new double[4][4]);
        setEntry(0, 0, 1);
        setEntry(1, 1, 1);
        setEntry(2, 2, 1);
        setEntry(3, 2, 1 / VIEWPORT_DISTANCE);
    }

    private void multiplyViewportLength(double value) {
        setEntry(3, 2, getEntry(3, 2) * value);
    }

    public void zoomIn() {
        multiplyViewportLength(1 / DEFAULT_ZOOM);
    }

    public void zoomOut() {
        multiplyViewportLength(DEFAULT_ZOOM);
    }

    public double getViewportDistance() {
        return VIEWPORT_DISTANCE * getEntry(3, 2);
    }
}
