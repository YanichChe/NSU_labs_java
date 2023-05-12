package ru.nsu.ccfit.chernovskaya.server;

/** Создание и запуск серверной части приложения */
public class Main {
    public static void main(String[] args) {
        ConfigParser configParser = new ConfigParser();
        new Thread(new Server(configParser)).start();
    }
}
