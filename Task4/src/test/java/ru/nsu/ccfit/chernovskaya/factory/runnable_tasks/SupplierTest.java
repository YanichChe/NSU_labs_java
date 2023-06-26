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
import static ru.nsu.ccfit.chernovskaya.GUI.Slider.SEC;

import java.util.stream.Stream;

public class SupplierTest{
    private static final int accessoryCapacity = 4;
    private static final int bodyCapacity = 5;
    private static final int motorCapacity = 6;
    private final int supplierDelay = 5000;

    @ParameterizedTest
    @MethodSource("generateSupplierData")
    public void testTestRun(Warehouse<Product> warehouse, Class<Product> productClass, int supplierDelay) throws InterruptedException {

       Supplier<?> supplier = new Supplier<>(warehouse, productClass);
       supplier.setSupplierDelay(supplierDelay);

       Thread thread = new Thread(supplier);

       thread.start();
       Thread.sleep(5000);
       thread.interrupt();

       assertThat(warehouse.getProducts().getFirst().getClass()).isEqualTo(productClass);
       assertThat(warehouse.getProducts().size()).isNotEqualTo(0);

    }

    @ParameterizedTest
    @MethodSource("generateSupplierData")
    @Test
    public void testGetSupplierDelay(Warehouse<Product> warehouse, Class<Product> productClass, int supplierDelay) {
        Supplier<?> supplier = new Supplier<>(warehouse, productClass);
        supplier.setSupplierDelay(supplierDelay);
        assertThat(supplier.getSupplierDelay()).isEqualTo(supplierDelay);
    }

    @ParameterizedTest
    @MethodSource("generateSupplierData")
    @Test
    public void testSetSupplierDelay(Warehouse<Product> warehouse, Class<Product> productClass, int supplierDelay) {
        Supplier<?> supplier = new Supplier<>(warehouse, productClass);
        supplier.setSupplierDelay(supplierDelay);
        supplier.setSupplierDelay(10 * SEC);
        assertThat(supplier.getSupplierDelay()).isEqualTo(10 * SEC);
    }

    private static Stream<Arguments> generateSupplierData()
    {
        return Stream.of(
                Arguments.of(new Warehouse<>(accessoryCapacity, Warehouse.ACCESSORY_WAREHOUSE_NAME), Accessory.class, 5000),
                Arguments.of(new Warehouse<>(bodyCapacity, Warehouse.BODY_WAREHOUSE_NAME), Body.class, 5000),
                Arguments.of(new Warehouse<>(motorCapacity, Warehouse.MOTOR_WAREHOUSE_NAME), Motor.class, 5000)
        );
    }
}