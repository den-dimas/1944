package main.entities.Varians;

import java.awt.*;

import main.Vector2D;
import main.entities.Enemy;
import main.utils.GameSettings;

public class Statistika extends Enemy {

    public Statistika() {
        super();
        health = 50;
        speed = 6;
    }

    @Override
    public void move() {
        super.move();
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.decode("#FF0000"));
        g.drawString("Statistika", (int) position.x, (int) position.y);
    }
}
