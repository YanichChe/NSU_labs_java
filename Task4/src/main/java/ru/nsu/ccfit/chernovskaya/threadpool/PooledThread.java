package ru.nsu.ccfit.chernovskaya.threadpool;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
@Setter
@Getter
public class PooledThread extends Thread {
     public List<Task> taskQueue;
     private int ID;

    public void run() {
        Task currentTask;
        while (true) {
            synchronized (taskQueue) {
                if (taskQueue.isEmpty()) {
                    try {
                        taskQueue.wait();
                        log.info(" no tasks");
                    }
                    catch (InterruptedException e) {
                        log.warn(taskQueue.get(0) + " was interrupted");
                    }
                    continue;
                }
                else {
                    currentTask = taskQueue.remove(0);
                }
            }
            try {
                currentTask.exec();
            } catch (InterruptedException e) {
                log.warn(e.getMessage());
            }
        }
    }
}