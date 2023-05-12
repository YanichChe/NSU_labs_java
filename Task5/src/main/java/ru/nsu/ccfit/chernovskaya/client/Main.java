package ru.nsu.ccfit.chernovskaya.client;

import ru.nsu.ccfit.chernovskaya.client.view.GUI;

/** Запуск клиентсвой части приложения */
public class Main {
    public static void main(String[] args) {
        Client client = new Client();
        GUI gui = new GUI(client);
        gui.setVisible(true);
        new Thread(client).start();
    }
}