package ru.nsu.ccfit.chernovskaya.factory.Tasks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import ru.nsu.ccfit.chernovskaya.factory.Warehouse;
import ru.nsu.ccfit.chernovskaya.product.auto.Auto;
import ru.nsu.ccfit.chernovskaya.threadpool.Task;

@AllArgsConstructor
@Getter
@Setter
@Log4j2
public class Dealer implements Task {
    private final String name;
    private final Warehouse<Auto> autoWarehouse;
    private int dealerDelay;

    @Override
    public String getTaskName() {
        return name;
    }

    @Override
    public void exec() throws InterruptedException {
        while (true) {
            Auto car = autoWarehouse.deliver();
            log.info(name + "bought : Auto<" + car.getID() + "> " +
                    "(Body:<" + car.getBody().getID() + ">, " +
                    "Motor:<" + car.getMotor().getID() + ">, " +
                    "Accessory:<" + car.getAccessory().getID() + ">)");
            Thread.sleep(dealerDelay);
        }
    }
}
