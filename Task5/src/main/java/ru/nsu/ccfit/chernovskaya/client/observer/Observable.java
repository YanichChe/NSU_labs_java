package ru.nsu.ccfit.chernovskaya.client.observer;

public interface Observable {
    void notifyObservers();
    void addObserver(Observer observer);
    void deleteObserver(Observer observer);
}