package com.example.virtualcamera.model.map;

public class Scene {
    public static final Cuboid[] DEFAULT_VIEW = new Cuboid[] {
            new Cuboid(500, 600, 3400, 900, -400, 3800),
            new Cuboid(-1000, 600, 3100, -300, -500, 3800),
            new Cuboid(500, 600, 1900, 1300, -100, 2300),
            new Cuboid(-800, 600, 1900, -300, -600, 3000)
    };
}
