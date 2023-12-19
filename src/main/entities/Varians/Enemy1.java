package main.entities.Varians;

import java.awt.*;

import main.entities.Enemy;
import main.utils.GameSettings;

public class Enemy1 extends Enemy{

    public Enemy1() {
        super();
        health = 300;
        speed = 1;
    }

    @Override
    public void move() {
        super.move();
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.decode("#38dd98"));
        g.drawString("Dasar Rangkaian Elektronika", (int) position.x, (int) position.y);
    }
}
