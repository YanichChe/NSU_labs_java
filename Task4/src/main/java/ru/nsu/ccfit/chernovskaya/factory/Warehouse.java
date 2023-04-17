package ru.nsu.ccfit.chernovskaya.factory;

import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.nsu.ccfit.chernovskaya.product.Product;
import java.util.ArrayDeque;

@Getter
@Setter
public class Warehouse<T extends Product> {

    private final String warehouseName;
    private final ArrayDeque<T> products;
    private final int warehouseCapacity;

    private final Logger logger = LogManager.getLogger(Warehouse.class);
    private final Object monitor = new Object();

    public Warehouse(int warehouseCapacity, String warehouseName) {
        this.products = new ArrayDeque<>();
        this.warehouseCapacity = warehouseCapacity;
        this.warehouseName = warehouseName;
        logger.info(warehouseName + " was create, capacity:"+ warehouseCapacity);
    }

    public void put (T newItem) throws InterruptedException {
        synchronized (monitor) {
            if (products.size() >= warehouseCapacity) {
                try {
                    logger.info(warehouseName + " is full");
                    monitor.wait();
                } catch (InterruptedException e) {
                    logger.info(warehouseName + " is interrupted");
                    throw e;
                }
            }
            logger.info(warehouseName + " got new product " + newItem.toString());
            products.add(newItem);
            monitor.notify();
            logger.info(warehouseName + " notified");
        }
    }

    public T deliver () throws InterruptedException {
        synchronized (monitor) {
            while (true) {
                try {
                    logger.info(warehouseName + " current size is " + products.size());
                    if (!products.isEmpty()) {
                        T item = products.getFirst();
                        products.remove();
                        monitor.notify();
                        logger.info(warehouseName + " is delivered ");
                        return item;
                    } else {
                        logger.info(warehouseName + " is waiting products");
                        monitor.wait();
                        logger.info(warehouseName + " continue");
                    }
                } catch (InterruptedException e) {
                    logger.info(warehouseName + " is interrupted");
                    throw e;
                }
            }
        }
    }
}
