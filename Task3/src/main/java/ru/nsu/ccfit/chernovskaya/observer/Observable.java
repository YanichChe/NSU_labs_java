package ru.nsu.ccfit.chernovskaya.observer;

public interface Observable {
    void notifyObservers(int data);
    void addObserver(Observer observer);
    void deleteObserver(Observer observer);
}