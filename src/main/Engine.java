package main;

import main.entities.*;
import main.utils.GameSettings;
import main.utils.KeyHandler;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

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

            enemy.position.x = enemyXDir == 1 ? Math.random() * -10 : Math.random() * 10 + GameSettings.screenDimension.width;
            enemy.position.y = enemyYDir == 1 ? Math.random() * -10 : Math.random() * 10 + GameSettings.screenDimension.height;

            enemies.add(enemy);

            enemyXDir *= -1;
            enemyYDir *= -1;
            enemyInterval = 0;
        }

        for (Enemy e : enemies) {
            e.move();
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

        g.dispose();
    }
}
