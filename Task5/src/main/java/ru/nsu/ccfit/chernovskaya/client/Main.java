package ru.nsu.ccfit.chernovskaya.client;

import ru.nsu.ccfit.chernovskaya.client.view.GUI;

/** Запуск клиентской части приложения */
public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Client client = new Client();
            GUI gui = new GUI(client);
            gui.setVisible(true);
            new Thread(client).start();
        }
    }
}