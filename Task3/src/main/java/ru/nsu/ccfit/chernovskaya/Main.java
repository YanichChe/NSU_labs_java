package main.java.ru.nsu.ccfit.chernovskaya;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            StartMenu startMenu = new StartMenu();
            startMenu.setVisible(true);
        });
    }
}
