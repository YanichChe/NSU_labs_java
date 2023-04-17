package ru.nsu.ccfit.chernovskaya.threadpool;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.nsu.ccfit.chernovskaya.product.Product;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ThreadPool{
    private final Logger logger = LogManager.getLogger(Product.class);
    private final List<Task> taskQueue = new LinkedList<>();

    public void addTask(Task task) {
        synchronized (taskQueue) {
            taskQueue.add(task);
            taskQueue.notify();
        }
    }

    public ThreadPool(int threadsCount) {
        Set<PooledThread> availableThreads = new HashSet<>();
        for (int i = 0; i < threadsCount; ++i) {
            availableThreads.add(new PooledThread("Thread " + i, taskQueue));
            logger.info("Thread " + i + "was creating");
        }
        for (PooledThread availableThread : availableThreads) {
            availableThread.start();
            logger.info("Thread " + availableThread.getName() +  " was creating");
        }
    }
}