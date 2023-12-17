package main.entities;

import main.Vector2D;
import main.utils.GameSettings;

public class BulletSpawner {
    public void spawnBullet10(BulletComposer bulletComposer) {
        bulletComposer.spawn();
        bulletComposer.setDamage(10);
        bulletComposer.setSpeed(15);
        bulletComposer.setInitialPosition(new Vector2D(
            Player.player.position.x + GameSettings.scaledTileSize / 3.0,
            Player.player.position.y + GameSettings.scaledTileSize / 3.0)
        );
    }
}
