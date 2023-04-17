package ru.nsu.ccfit.chernovskaya.threadpool;

public interface Task {
    String getTaskName();
    void exec() throws InterruptedException;
}