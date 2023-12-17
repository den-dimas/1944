package main;

import main.utils.GameSettings;

import java.awt.*;

public class Body2D {
    public Vector2D position = new Vector2D(0, 0);
    public int speed = 5;
    public int health = 100;

    public void move() {

    }
    public void draw(Graphics2D g) {

    }
    public Vector2D direction() { return null; }

    public Rectangle bounds(int size) {
        return new Rectangle((int) position.x, (int) position.y, size, size);
    }
}
