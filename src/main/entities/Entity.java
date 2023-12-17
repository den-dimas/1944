package main.entities;

import main.Engine;

public interface Entity {
    void setDamage(int damage);
    void setHealth(int health);
//    void setHitbox(int setHitbox);
    void spawn();
}
