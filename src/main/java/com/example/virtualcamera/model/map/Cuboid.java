package com.example.virtualcamera.model.map;

import com.example.virtualcamera.model.transformations.Point;
import com.example.virtualcamera.model.transformations.ProjectionMatrix;
import com.example.virtualcamera.view.View;
import javafx.scene.canvas.GraphicsContext;
import org.apache.commons.math3.linear.RealMatrix;

import java.util.Arrays;

public class Cuboid {

    /**
     * Points names:
     *
     *     D'------------------C'
     *   / |                  /|
     *  /  |                 / |
     * A'-------------------B' |
     * |   |                |  |
     * |   D- - - - - - - - |- C
     * |  /                 | /
     * | /                  |/
     * A--------------------B
     */
    private final int A = 0;
    private final int B = 1;
    private final int C = 2;
    private final int D = 3;
    private final int A_PRIME = 4;
    private final int B_PRIME = 5;
    private final int C_PRIME = 6;
    private final int D_PRIME = 7;

    private final Point[] vertices;

    public Cuboid(double leftX, double downY, double frontZ, double rightX, double upY, double backZ) {
        vertices = new Point[8];
        vertices[A] = new Point(leftX, downY, frontZ);
        vertices[B] = new Point(rightX, downY, frontZ);
        vertices[C] = new Point(rightX, downY, backZ);
        vertices[D] = new Point(leftX, downY, backZ);
        vertices[A_PRIME] = new Point(leftX, upY, frontZ);
        vertices[B_PRIME] = new Point(rightX, upY, frontZ);
        vertices[C_PRIME] = new Point(rightX, upY, backZ);
        vertices[D_PRIME] = new Point(leftX, upY, backZ);
    }

    public Cuboid(Point[] vertices) {
        if (vertices.length != 8) {
            throw new IllegalArgumentException("Array has wrong size.");
        }
        this.vertices = Arrays.copyOf(vertices, 8);
    }

    public Cuboid copy() {
        Point[] vertices = new Point[8];
        for (int i = 0; i < 8; i++) {
            Point vertex = this.vertices[i];
            vertex.normalize();
            vertices[i] = new Point(vertex.getX(), vertex.getY(), vertex.getZ());
        }
        return new Cuboid(vertices);
    }

    private Point getCommonPoint(Point first, Point second, double viewportDistance) {
        double t = (viewportDistance - first.getZ()) / (second.getZ() - first.getZ());
        double x = first.getX() + (second.getX() - first.getX()) * t;
        double y = first.getY() + (second.getY() - first.getY()) * t;
        return new Point(x, y, viewportDistance);
    }

    private void drawEdge(int firstIndex, int secondIndex, GraphicsContext gc, ProjectionMatrix projectionMatrix) {
        Point first = vertices[firstIndex];
        Point second = vertices[secondIndex];
        double viewportDistance = projectionMatrix.getViewportDistance();

        if (first.getZ() >= viewportDistance && second.getZ() >= viewportDistance) {
            first = first.transform(projectionMatrix);
            second = second.transform(projectionMatrix);
            gc.strokeLine(first.getX() + View.WIDTH / 2, first.getY() + View.HEIGHT / 2,
                    second.getX() + View.WIDTH / 2, second.getY() + View.HEIGHT / 2);
        }
        else if (first.getZ() < viewportDistance && second.getZ() > viewportDistance) {
            first = getCommonPoint(first, second, viewportDistance);
            first = first.transform(projectionMatrix);
            second = second.transform(projectionMatrix);
            gc.strokeLine(first.getX() + View.WIDTH / 2, first.getY() + View.HEIGHT / 2,
                    second.getX() + View.WIDTH / 2, second.getY() + View.HEIGHT / 2);
        } else if (first.getZ() > viewportDistance && second.getZ() < viewportDistance) {
            second = getCommonPoint(first, second, viewportDistance);
            first = first.transform(projectionMatrix);
            second = second.transform(projectionMatrix);
            gc.strokeLine(first.getX() + View.WIDTH / 2, first.getY() + View.HEIGHT / 2,
                    second.getX() + View.WIDTH / 2, second.getY() + View.HEIGHT / 2);
        }
    }

    public Cuboid transform(RealMatrix matrix) {
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = vertices[i].transform(matrix);
        }
        return this;
    }

    public void draw(GraphicsContext gc, ProjectionMatrix projectionMatrix) {
        drawEdge(A, B, gc, projectionMatrix);
        drawEdge(B, C, gc, projectionMatrix);
        drawEdge(C, D, gc, projectionMatrix);
        drawEdge(A, D, gc, projectionMatrix);

        drawEdge(A_PRIME, B_PRIME, gc, projectionMatrix);
        drawEdge(B_PRIME, C_PRIME, gc, projectionMatrix);
        drawEdge(C_PRIME, D_PRIME, gc, projectionMatrix);
        drawEdge(A_PRIME, D_PRIME, gc, projectionMatrix);

        drawEdge(A, A_PRIME, gc, projectionMatrix);
        drawEdge(B, B_PRIME, gc, projectionMatrix);
        drawEdge(C, C_PRIME, gc, projectionMatrix);
        drawEdge(D, D_PRIME, gc, projectionMatrix);
    }
}
