package main;

public class Vector2D {
    public double x;
    public double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D(Vector2D vector2D) {
        this.x = vector2D.x;
        this.y = vector2D.y;
    }

    public Vector2D() {
        this.x = 0;
        this.y = 0;
    }

    public void normalized() {
        if (x != 0 || y != 0) {
            double mag = Math.sqrt((x * x) + (y * y));

            x = x / mag;
            y = y / mag;
        } else {
            x = 0;
            y = 0;
        }

    }
}
