package main.entities;

import main.Body2D;
import main.Vector2D;
import main.utils.GameSettings;

import java.awt.*;

public class Enemy extends Body2D {
    int size = GameSettings.scaledTileSize;
    int speed = 2;

    public Enemy() {
        position.x = 0;
        position.y = 0;
    }

    @Override
    public void move() {
        Vector2D ppos = new Vector2D(Player.player.position);
        Vector2D dir = new Vector2D(ppos.x - position.x, ppos.y - position.y);

        dir.normalized();

        position.x += dir.x * speed;
        position.y += dir.y * speed;

//        Move left and right
//        position.x += speed * direction;
//
//        if ((bounds(size).x + size) >= GameSettings.screenDimension.width) {
//            direction = -1;
//        } else if (bounds(size).x <= 0) {
//            direction = 1;
//        }
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.decode("#80a6d7"));
        g.fill(bounds(size));
    }
}
