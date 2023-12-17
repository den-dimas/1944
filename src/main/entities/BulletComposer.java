package main.entities;

import main.Vector2D;
import main.utils.GameSettings;

public class BulletComposer implements Entity {
    private static Bullet bullet;

    public BulletComposer() { spawn(); }

    public void setInitialPosition(Vector2D initialPosition) {
        bullet.position = initialPosition;
    }

    public void setSpeed(int speed) {
        bullet.speed = speed;
    }

    @Override
    public void setDamage(int damage) {
        bullet.damage = damage;
    }

    @Override
    public void spawn() {
        bullet = new Bullet();
    }

    public Bullet getBullet() {
        Bullet b = bullet;
        spawn();
        return b;
    }

    @Override
    public void setHealth(int health) {}
}
