package ru.nsu.ccfit.chernovskaya.factory.worker;

import lombok.extern.log4j.Log4j2;
import ru.nsu.ccfit.chernovskaya.threadpool.PooledThread;

@Log4j2
public class Worker extends PooledThread {

    public static int global_ID = 0;

    public Worker() {
        super.setID(global_ID);
        global_ID++;
        log.info("Worker:" + super.getID() + " was created");
    }
}
