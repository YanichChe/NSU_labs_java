package ru.nsu.ccfit.chernovskaya.factory.worker;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import ru.nsu.ccfit.chernovskaya.factory.product.auto.Accessory;
import ru.nsu.ccfit.chernovskaya.factory.product.auto.Auto;
import ru.nsu.ccfit.chernovskaya.factory.product.auto.Body;
import ru.nsu.ccfit.chernovskaya.factory.product.auto.Motor;
import ru.nsu.ccfit.chernovskaya.factory.warehouse.Warehouse;
import ru.nsu.ccfit.chernovskaya.threadpool.Task;

@RequiredArgsConstructor
@Log4j2
public class BuildCar implements Task {
    private final Warehouse<Accessory> accessoryWarehouse;
    private final Warehouse<Motor> motorWarehouse;
    private final Warehouse<Body> bodyWarehouse;
    private final Warehouse<Auto> autoWarehouse;

    @Override
    public void exec() throws InterruptedException {
        Accessory accessory= accessoryWarehouse.deliver();
        Body body = bodyWarehouse.deliver();
        Motor motor = motorWarehouse.deliver();

        Auto auto = new Auto(accessory, body, motor);
        autoWarehouse.put(auto);

        log.info(Thread.currentThread() + " build");
    }
}
