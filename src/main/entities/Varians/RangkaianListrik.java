package main.entities.Varians;

import java.awt.*;

import main.entities.Enemy;
import main.utils.GameSettings;

public class RangkaianListrik extends Enemy{

    public RangkaianListrik() {
        super();
        health = 200;
        speed = 1;
    }

    @Override
    public void move() {
        super.move();
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.decode("#80ad32"));
        g.drawString("Rangkaian Listrik", (int) position.x, (int) position.y);
    }
}
