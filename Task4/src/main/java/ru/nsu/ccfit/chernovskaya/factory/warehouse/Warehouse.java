package ru.nsu.ccfit.chernovskaya.factory.warehouse;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import ru.nsu.ccfit.chernovskaya.factory.product.Product;
import java.util.ArrayDeque;

@Getter
@Setter
@Log4j2
public class Warehouse<T extends Product> {

    public final static String ACCESSORY_WAREHOUSE_NAME = "Accessory warehouse";
    public final static String BODY_WAREHOUSE_NAME = "Body warehouse";
    public final static String MOTOR_WAREHOUSE_NAME = "Motor warehouse";
    public final static String AUTO_WAREHOUSE_NAME = "Auto warehouse";

    private final String warehouseName;
    private final ArrayDeque<T> products;
    private final int warehouseCapacity;

    private final Object monitor = new Object();

    public Warehouse(int warehouseCapacity, String warehouseName) {
        this.products = new ArrayDeque<>();
        this.warehouseCapacity = warehouseCapacity;
        this.warehouseName = warehouseName;
        log.info(warehouseName + " was create, capacity:"+ warehouseCapacity);
    }

    public void put (T newItem) throws InterruptedException {
        synchronized (monitor) {
            if (products.size() >= warehouseCapacity) {
                try {
                    log.info(warehouseName + " is full");
                    monitor.wait();
                } catch (InterruptedException e) {
                    log.info(warehouseName + " is interrupted");
                    throw e;
                }
            }
            log.info(warehouseName + " got new product " + newItem.getClass().getName() + " ID: " + newItem.getID());
            products.add(newItem);
            monitor.notify();
        }
    }

    public T deliver () throws InterruptedException {
        synchronized (monitor) {
            while (true) {
                try {
                    log.info(warehouseName + " current size is " + products.size());
                    if (!products.isEmpty()) {
                        T item = products.getFirst();
                        products.remove();
                        monitor.notify();
                        log.info(warehouseName + " delivered ");
                        return item;
                    } else {
                        log.info(warehouseName + " is waiting products");
                        monitor.wait();
                        log.info(warehouseName + " continue");
                    }
                } catch (InterruptedException e) {
                    log.info(warehouseName + " is interrupted");
                    throw e;
                }
            }
        }
    }
}
