package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();

        defaultSettings(window);
    }

    private static void defaultSettings(JFrame window) {
        Engine engine = new Engine();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("1944");


        window.add(engine);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        engine.startGameThread();
    }
}