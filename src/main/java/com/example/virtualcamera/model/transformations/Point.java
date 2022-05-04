package com.example.virtualcamera.model.transformations;

import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.RealMatrix;

public class Point extends Array2DRowRealMatrix {

    public Point(double x, double y, double z) {
        super(new double[][] {{x}, {y}, {z}, {1}});
    }

    public Point(RealMatrix matrix) {
        super(matrix.getData());
    }

    public double getX() {
        return this.getEntry(0, 0);
    }

    public double getY() {
        return this.getEntry(1, 0);
    }

    public double getZ() {
        return this.getEntry(2, 0);
    }

    public double getN() {
        return this.getEntry(3, 0);
    }

    public void setX(double value) {
        this.setEntry(0, 0, value);
    }

    public void setY(double value) {
        this.setEntry(1, 0, value);
    }

    public void setZ(double value) {
        this.setEntry(2, 0, value);
    }

    public void normalize() {
        for (int i = 0; i < 4; i++) {
            setEntry(i, 0, getEntry(i, 0) / getN());
        }
    }

    public Point transform(RealMatrix matrix) {
        Point point = new Point(matrix.multiply(this));
        point.normalize();
        return point;
    }
}
