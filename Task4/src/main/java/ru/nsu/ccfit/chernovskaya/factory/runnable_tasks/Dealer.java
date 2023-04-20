package ru.nsu.ccfit.chernovskaya.factory.runnable_tasks;

import lombok.*;
import lombok.extern.log4j.Log4j2;
import ru.nsu.ccfit.chernovskaya.factory.product.auto.Auto;
import ru.nsu.ccfit.chernovskaya.factory.warehouse.Warehouse;

@Log4j2
public class Dealer implements Runnable{

    private final Warehouse<Auto> autoWarehouse;
    @Getter @Setter private int dealerDelay;
    @Getter private Auto currentAuto;

    public Dealer(Warehouse<Auto> autoWarehouse, int dealerDelay){
        this.autoWarehouse = autoWarehouse;
        this.dealerDelay = dealerDelay;
        log.info("Dealer was creates");
    }

    @Override
    public void run() {
        while (true) {
            try {
                currentAuto = autoWarehouse.deliver();
                log.info("Dealer bought : Auto<" + currentAuto.getID() + "> " + "(Body:<" + currentAuto.getBody().getID() + ">, " + "Motor:<" + currentAuto.getMotor().getID() + ">, " + "Accessory:<" + currentAuto.getAccessory().getID() + ">)");

                try {
                    Thread.sleep(dealerDelay);
                }
                catch (InterruptedException e) {
                    log.warn(e.getMessage());
                }
            }
            catch (InterruptedException e){
                log.warn(e.getMessage());
            }

        }

    }
}
