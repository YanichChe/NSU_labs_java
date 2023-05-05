package ru.nsu.ccfit.chernovskaya.threadpool;

public interface Task {
    void exec() throws InterruptedException;
}