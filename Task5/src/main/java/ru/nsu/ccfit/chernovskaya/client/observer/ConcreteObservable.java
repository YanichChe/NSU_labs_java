package ru.nsu.ccfit.chernovskaya.client.observer;

import java.util.ArrayList;
import java.util.List;

public class ConcreteObservable implements Observable {
    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void deleteObserver(Observer observer) {
        observers.remove(observer);
    }
}