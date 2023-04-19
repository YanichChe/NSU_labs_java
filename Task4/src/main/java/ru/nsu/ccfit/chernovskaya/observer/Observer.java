package ru.nsu.ccfit.chernovskaya.observer;

public interface Observer {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
