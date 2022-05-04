package com.example.virtualcamera.model.transformations;
import org.apache.commons.math3.linear.Array2DRowRealMatrix;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

public class PositionMatrix extends Array2DRowRealMatrix{
    public static final double DEFAULT_DISTANCE = 200;
    public static final double DEFAULT_ANGLE = Math.PI / 20;

    public PositionMatrix() {
        super(MatrixUtils.createRealIdentityMatrix(4).getData());
    }

    private RealMatrix getTranslationMatrix(double x, double y, double z) {
        return new Array2DRowRealMatrix(new double[][] {
                { 1, 0, 0, x },
                { 0, 1, 0, y },
                { 0, 0, 1, z },
                { 0, 0, 0, 1 },
        });
    }

    private RealMatrix getRotationMatrixAxisX(double angle) {
        return new Array2DRowRealMatrix(new double[][]{
                {1, 0, 0, 0},
                {0, Math.cos(angle), -Math.sin(angle), 0},
                {0, Math.sin(angle), Math.cos(angle), 0},
                {0, 0, 0, 1}
        }, false);
    }

    private RealMatrix getRotationMatrixAxisY(double angle) {
        return new Array2DRowRealMatrix(new double[][]{
                {Math.cos(angle), 0, Math.sin(angle), 0},
                {0, 1, 0, 0},
                {-Math.sin(angle), 0, Math.cos(angle), 0},
                {0, 0, 0, 1}
        }, false);
    }

    private RealMatrix getRotationMatrixAxisZ(double angle) {
        return new Array2DRowRealMatrix(new double[][] {
                { Math.cos(angle), -Math.sin(angle), 0, 0 },
                { Math.sin(angle), Math.cos(angle), 0, 0 },
                { 0, 0, 1, 0 },
                { 0, 0, 0, 1 }
        }, false);
    }

    private void set(RealMatrix matrix) {
        setSubMatrix(matrix.getData(), 0, 0);
    }

    public void translate(double x, double y, double z) {
        set(getTranslationMatrix(x, y, z).multiply(this));
    }

    public void rotateX(double angle) {
        set(getRotationMatrixAxisX(angle).multiply(this));
    }

    public void rotateY(double angle) {
        set(getRotationMatrixAxisY(angle).multiply(this));
    }

    public void rotateZ(double angle) {
        set(getRotationMatrixAxisZ(angle).multiply(this));
    }


}
