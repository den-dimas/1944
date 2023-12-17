package main.utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public int moveUp, moveDown, moveLeft, moveRight;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) moveUp = 1;
        if (code == KeyEvent.VK_S) moveDown = 1;
        if (code == KeyEvent.VK_D) moveRight = 1;
        if (code == KeyEvent.VK_A) moveLeft = 1;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) moveUp = 0;
        if (code == KeyEvent.VK_S) moveDown = 0;
        if (code == KeyEvent.VK_D) moveRight = 0;
        if (code == KeyEvent.VK_A) moveLeft = 0;
    }
}
