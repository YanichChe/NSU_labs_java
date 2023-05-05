package ru.nsu.ccfit.chernovskaya.factory.worker;

import lombok.extern.log4j.Log4j2;
import ru.nsu.ccfit.chernovskaya.threadpool.Task;
import ru.nsu.ccfit.chernovskaya.threadpool.ThreadPool;

@Log4j2
public class WorkerThreadPool {
    private final ThreadPool threadPool = new ThreadPool();

    public WorkerThreadPool(int workerCount) {
        for (int i = 0; i < workerCount; i++) {
            Worker worker = new Worker();
            threadPool.addThread(worker);
        }
    }

    public int getCurrentQueueTasksSize()
    {
        return threadPool.getTaskQueueSize();
    }

    public void addTask(Task task) {
        threadPool.addTask(task);
    }

    public void startWork() {
        threadPool.start();
    }
}
