package ru.nsu.ccfit.chernovskaya.threadpool;

import lombok.Getter;
import ru.nsu.ccfit.chernovskaya.factory.Tasks.Dealer;
import ru.nsu.ccfit.chernovskaya.factory.Tasks.Supplier;
import ru.nsu.ccfit.chernovskaya.factory.Tasks.Worker;
import ru.nsu.ccfit.chernovskaya.product.auto.Accessory;
import ru.nsu.ccfit.chernovskaya.product.auto.Body;
import ru.nsu.ccfit.chernovskaya.product.auto.Motor;

import java.util.ArrayList;

@Getter
public class TasksList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    public TasksList(
            Supplier<Accessory> accessorySupplier,
            Supplier<Body> bodySupplier,
            Supplier<Motor> motorSupplier,

            ArrayList<Dealer> dealerArrayList,
            ArrayList<Worker> workerArrayList
    ) {
        tasks.addAll(dealerArrayList);
        tasks.addAll(workerArrayList);

        tasks.add(bodySupplier);
        tasks.add(motorSupplier);
        tasks.add(accessorySupplier);

    }

}