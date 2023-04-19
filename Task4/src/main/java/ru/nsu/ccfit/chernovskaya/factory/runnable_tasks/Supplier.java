package ru.nsu.ccfit.chernovskaya.factory.runnable_tasks;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import ru.nsu.ccfit.chernovskaya.factory.product.Product;
import ru.nsu.ccfit.chernovskaya.factory.warehouse.Warehouse;

import java.lang.reflect.InvocationTargetException;

@Log4j2

public class Supplier<T extends Product> implements Runnable{

    private final Warehouse<T> warehouse;
    private final Class<T> productClass;

    @Getter
    @Setter
    private int supplierDelay;

    public Supplier(Warehouse<T> warehouse, Class<T> productClass, int supplierDelay) {
        this.warehouse = warehouse;
        this.productClass = productClass;
        this.supplierDelay = supplierDelay;
        log.info("Supplier for " + warehouse.getWarehouseName() + " with delay " + supplierDelay + " was created");
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try {
                T product = productClass.getDeclaredConstructor().newInstance();
                warehouse.put(product);
                Thread.sleep(supplierDelay);
            } catch (InterruptedException e) {
                log.info(Thread.currentThread().getName() + " was interrupted");
                break;
            }
            catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
                log.error(e.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }
}
