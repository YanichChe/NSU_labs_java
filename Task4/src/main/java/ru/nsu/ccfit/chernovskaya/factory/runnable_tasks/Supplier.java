package ru.nsu.ccfit.chernovskaya.factory.runnable_tasks;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import ru.nsu.ccfit.chernovskaya.factory.product.Product;
import ru.nsu.ccfit.chernovskaya.factory.warehouse.Warehouse;

import java.lang.reflect.InvocationTargetException;

@Log4j2
@RequiredArgsConstructor
public class Supplier<T extends Product> implements Runnable{

    private final Warehouse<T> warehouse;
    private final Class<T> productClass;

    @Getter
    @Setter
    private int supplierDelay;

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try {
                Thread.sleep(supplierDelay);
                T product = productClass.getDeclaredConstructor().newInstance();
                warehouse.put(product);
            } catch (InterruptedException e) {
                log.info(Thread.currentThread().getName() + " was interrupted");
                break;
            }
            catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}
