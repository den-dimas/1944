package main.entities.Varians;

import java.awt.*;

import main.entities.Enemy;
import main.utils.GameSettings;

public class Enemy4 extends Enemy{

    public Enemy4() {
        super();
        speed = 3;
        health = 150;
    }

    @Override
    public void move() {
        super.move();
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.decode("#2347dd"));
        g.drawString("Strukdis", (int) position.x, (int) position.y);
    }
}
