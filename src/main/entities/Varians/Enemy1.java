package main.entities.Varians;

import java.awt.*;

import main.entities.Enemy;
import main.utils.GameSettings;

public class Enemy1 extends Enemy{

    public Enemy1() {
        super();
    }

    @Override
    public void move() {
        super.move();
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.decode("#80ad32"));
        g.fill(bounds(GameSettings.scaledTileSize));
    }
}
