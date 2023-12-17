package main.entities;

import main.Body2D;
import main.Engine;
import main.Vector2D;
import main.utils.GameSettings;
import main.utils.KeyHandler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player extends Body2D {
    KeyHandler keyHandler;
    public static Player player;
    public int size = GameSettings.scaledTileSize;

    List<Bullet> bullets = new ArrayList<>();
    BulletSpawner bulletSpawner = new BulletSpawner();
    double fireRate = 8;
    double fireInterval = 0;

    Enemy targeted;
    Vector2D targetedLocation;

    public Player(KeyHandler keyHandler) {
        this.keyHandler = keyHandler;

        position.x = (GameSettings.screenDimension.width / 2.0) - (size / 2.0);
        position.y = (GameSettings.screenDimension.height / 2.0) - (size / 2.0);

        player = this;
    }

    public void fire(double delta) {
        if (Engine.enemies.isEmpty()) return;

        fireInterval += delta;

        trackEnemiesPosition();

        if (fireInterval >= fireRate) {
            BulletComposer bulletComposer = new BulletComposer();

            bulletSpawner.spawnBullet10(bulletComposer);

            bullets.add(bulletComposer.getBullet());
            fireInterval = 0;
        }

        for (int i = 0; i < bullets.size(); i++) {
            Bullet b = bullets.get(i);

            b.move(new Vector2D(targetedLocation.x - position.x, targetedLocation.y - position.y));

            if (b.position.y < 0 || b.position.y >= GameSettings.screenDimension.height || b.position.x < 0 || b.position.x >= GameSettings.screenDimension.width) {
                bullets.remove(i);
                break;
            }

            for (int j = 0; j < Engine.enemies.size(); j++) {
                Enemy e = Engine.enemies.get(j);

                if (b.bounds(b.size).intersects(e.bounds(e.size))) {
                    bullets.remove(i);
                    e.health -= b.damage;
                    break;
                }

                if (e.health == 0) {
                    Engine.enemies.remove(j);
                    break;
                }
            }
        }
    }

    void trackEnemiesPosition() {
        List<Enemy> enemies = Engine.enemies;
        int size = Engine.enemies.size();

        if (size < 1) return;

        List<Double> distances = new ArrayList<>();

        for (Enemy e : enemies) {
            distances.add(Math.sqrt(Math.pow(position.x - e.position.x, 2) + Math.pow(position.y - e.position.y, 2)));
        }

        List<Double> dTemp = new ArrayList<>(distances);
        Collections.sort(dTemp);

        double min = dTemp.get(0);
        int closest_index = distances.indexOf(min);

        targeted = enemies.get(closest_index);
        targetedLocation = targeted.position;
    }

    public void draw(Graphics2D g) {
        for (Bullet b : bullets) {
            if (b != null)
                b.draw(g);
        }

        g.setColor(Color.decode("#f8f8f8"));
        g.fill(this.bounds(size));
    }

    public void move() {
        Vector2D dir = direction();

        Vector2D leftBottomLimit = new Vector2D(position.x, position.y + size);
        Vector2D rightTopLimit = new Vector2D(position.x + size, position.y);

        if (dir.x < 0 && leftBottomLimit.x + dir.x * speed < 0 ||
            dir.x > 0 && rightTopLimit.x + dir.x * speed > GameSettings.screenDimension.width
        )
            dir.x = 0;

        if (dir.y < 0 && rightTopLimit.y + dir.y * speed < 0 ||
            dir.y > 0 && leftBottomLimit.y + dir.y * speed > GameSettings.screenDimension.height
        )
            dir.y = 0;

        position.y += dir.y * speed;
        position.x += dir.x * speed;
    }

    public Vector2D direction() {
        int vertical = keyHandler.moveDown - keyHandler.moveUp;
        int horizontal = keyHandler.moveRight - keyHandler.moveLeft;

        double dx = speed * horizontal;
        double dy = speed * vertical;
        double mag = Math.sqrt((dx * dx) + (dy * dy));

        if (mag != 0)
            return new Vector2D(dx / mag, dy / mag);

        return new Vector2D(0, 0);
    }
}