package ru.nsu.ccfit.chernovskaya.threadpool;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.nsu.ccfit.chernovskaya.product.Product;

import java.util.List;

public class PooledThread extends Thread {
    private final List<Task> taskQueue;
    private final Logger logger = LogManager.getLogger(Product.class);


    public PooledThread(String name, List<Task> taskQueue) {
        super(name);
        this.taskQueue = taskQueue;
    }

    public void run() {
        Task currentTask;
        while (true) {
            synchronized (taskQueue) {
                if (taskQueue.isEmpty()) {
                    try {
                        taskQueue.wait();
                        logger.info("no tasks");
                    }
                    catch (InterruptedException e) {
                        logger.warn(taskQueue.get(0) + "was interrupted");
                    }
                    continue;
                }
                else {
                    currentTask = taskQueue.remove(0);
                }
            }
            try {
                currentTask.exec();
                logger.info(currentTask.getTaskName() + "start");
            } catch (InterruptedException e) {
                logger.warn(e.getMessage());
            }
        }
    }
}