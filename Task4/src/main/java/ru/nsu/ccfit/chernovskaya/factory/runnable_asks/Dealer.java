package ru.nsu.ccfit.chernovskaya.factory.runnable_asks;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import ru.nsu.ccfit.chernovskaya.factory.product.auto.Auto;
import ru.nsu.ccfit.chernovskaya.factory.warehouse.Warehouse;

@RequiredArgsConstructor
@Log4j2
public class Dealer implements Runnable{

    private final Warehouse<Auto> autoWarehouse;

    @Getter
    @Setter
    private int dealerDelay;

    @SneakyThrows
    @Override
    public void run() {
        while (true) {
            Auto car = autoWarehouse.deliver();
            log.info("Dealer bought : Auto<" + car.getID() + "> " +
                    "(Body:<" + car.getBody().getID() + ">, " +
                    "Motor:<" + car.getMotor().getID() + ">, " +
                    "Accessory:<" + car.getAccessory().getID() + ">)");
            Thread.sleep(dealerDelay);
        }

    }
}
