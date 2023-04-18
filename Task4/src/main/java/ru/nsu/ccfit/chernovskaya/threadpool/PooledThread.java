package ru.nsu.ccfit.chernovskaya.threadpool;

import lombok.extern.log4j.Log4j2;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.nsu.ccfit.chernovskaya.product.Product;

import java.util.List;

@Log4j2
public class PooledThread extends Thread {
    private final List<Task> taskQueue;

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
                        log.info("no tasks");
                    }
                    catch (InterruptedException e) {
                        log.warn(taskQueue.get(0) + "was interrupted");
                    }
                    continue;
                }
                else {
                    currentTask = taskQueue.remove(0);
                }
            }
            try {
                currentTask.exec();
                log.info(currentTask.getTaskName() + "start");
            } catch (InterruptedException e) {
                log.warn(e.getMessage());
            }
        }
    }
}