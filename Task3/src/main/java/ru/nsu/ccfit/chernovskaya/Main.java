package ru.nsu.ccfit.chernovskaya;

import ru.nsu.ccfit.chernovskaya.view.StartMenu;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            final StartMenu startMenu = new StartMenu();
        });
    }
}
