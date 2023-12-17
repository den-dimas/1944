package main.entities;

import main.Body2D;
import main.Engine;
import main.Vector2D;
import main.utils.GameSettings;

import java.awt.*;

public class Bullet extends Body2D {
    public int damage;
    public int size = GameSettings.scaledTileSize / 3;
    Vector2D t;

    public void move(Vector2D target) {
        if (t == null) {
            t = new Vector2D(target);
            t.normalized();

            return;
        }

        position.x += t.x * speed;
        position.y += t.y * speed;
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.decode("#f8f8f8"));
        g.draw(bounds(size));
    }
}
