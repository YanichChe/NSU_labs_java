package main.java.ru.nsu.ccfit.chernovskaya;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            Tetris game = new Tetris();
            game.setVisible(true);
            game.init();
            game.start();
        });
    }
}
