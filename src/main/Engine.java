package main;

import main.entities.*;
import main.entities.Varians.Enemy1;
import main.entities.Varians.Enemy2;
import main.entities.Varians.Enemy3;
import main.utils.GameSettings;
import main.utils.KeyHandler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Engine extends JPanel implements Runnable {
    public static Engine engine;
    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();

    Player player = new Player(keyHandler);

    public static List<Enemy> enemies = new ArrayList<>();
    double enemyRate = 100;
    double enemyInterval = 0;
    int enemyXDir = 1;
    int enemyYDir = 1;

    public Engine() {
        setPreferredSize(GameSettings.screenDimension);
        setBackground(Color.decode("#181818"));
        super.setDoubleBuffered(true);

        addKeyListener(keyHandler);
        setFocusable(true);

        engine = this;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double interval = 10e8 / GameSettings.FPS;
        double delta = 0;

        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / interval;

            lastTime = currentTime;

            if (delta >= 1) {
                update(delta);
                repaint();

                delta--;
            }
        }
    }

    public void update(double delta) {
        player.move();

        enemyInterval += delta;

        if (enemyInterval >= enemyRate) {
            Enemy enemy = new Enemy();
            Enemy1 enemy1 = new Enemy1();
            Enemy2 enemy2 = new Enemy2();
            Enemy3 enemy3 = new Enemy3();

            enemy.position.x = enemyXDir == 1 ? Math.random() * -10 : Math.random() * 10 + GameSettings.screenDimension.width;
            enemy.position.y = enemyYDir == 1 ? Math.random() * -10 : Math.random() * 10 + GameSettings.screenDimension.height;

            Random random = new Random();
            int enemyType = random.nextInt(4);

            switch (enemyType) {
                case 0:
                    enemies.add(enemy);
                    break;
                case 1:
                    enemies.add(enemy1);
                    break;
                case 2:
                    enemies.add(enemy2);
                    break;
                case 3:
                    enemies.add(enemy3);
                    break;
            }

            enemyXDir *= -1;
            enemyYDir *= -1;
            enemyInterval = 0;
        }

        for (Enemy e : enemies) {
            e.move();

            if(e.health <= 0){
                player.updateScore(10);
                enemies.remove(e);
                break;
            }

            if (e.bounds(e.size).intersects(player.bounds(player.size))) {
                player.health -= 10;
                enemies.remove(e);
                break;
            }
        }

        player.fire(delta);
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D g = (Graphics2D) graphics;

        for (Enemy e : enemies) {
            e.draw(g);
        }

        player.draw(g);

        g.setColor(Color.WHITE);
        g.drawString("Score: " + player.getScore(), 10, 20);

        g.drawString("Health: " + player.health, 10, 40);

        g.dispose();
    }
}
