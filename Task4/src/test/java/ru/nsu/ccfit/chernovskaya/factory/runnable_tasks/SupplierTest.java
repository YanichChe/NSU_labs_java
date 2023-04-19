package ru.nsu.ccfit.chernovskaya.factory.runnable_tasks;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.nsu.ccfit.chernovskaya.factory.product.Product;
import ru.nsu.ccfit.chernovskaya.factory.product.auto.Accessory;
import ru.nsu.ccfit.chernovskaya.factory.product.auto.Body;
import ru.nsu.ccfit.chernovskaya.factory.product.auto.Motor;
import ru.nsu.ccfit.chernovskaya.factory.warehouse.Warehouse;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;

public class SupplierTest{
    private static final int accessoryCapacity = 4;
    private static final int bodyCapacity = 5;
    private static final int motorCapacity = 6;
    private final int supplierDelay = 5;

    @ParameterizedTest
    @MethodSource("generateSupplierData")
    public void testTestRun(Warehouse<Product> warehouse, Class<Product> productClass, int supplierDelay) throws InterruptedException {

       Supplier<?> supplier = new Supplier<>(warehouse, productClass, supplierDelay);
       Thread thread = new Thread(supplier);

       thread.start();
       Thread.sleep(supplierDelay);
       thread.interrupt();

       assertThat(warehouse.getProducts().getFirst().getClass()).isEqualTo(productClass);
       assertThat(warehouse.getProducts().size()).isEqualTo(1);

    }

    @Test
    public void testGetSupplierDelay() {
    }

    @Test
    public void testSetSupplierDelay() {
    }

    private static Stream<Arguments> generateSupplierData()
    {
        return Stream.of(
                Arguments.of(new Warehouse<>(accessoryCapacity, Warehouse.ACCESSORY_WAREHOUSE_NAME), Accessory.class, 5),
                Arguments.of(new Warehouse<>(bodyCapacity, Warehouse.BODY_WAREHOUSE_NAME), Body.class, 5),
                Arguments.of(new Warehouse<>(motorCapacity, Warehouse.MOTOR_WAREHOUSE_NAME), Motor.class, 5)
        );
    }
}