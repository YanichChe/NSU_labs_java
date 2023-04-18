package ru.nsu.ccfit.chernovskaya.factory.product.auto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import ru.nsu.ccfit.chernovskaya.factory.product.Product;

@Getter
@Setter
@ToString
@Log4j2
public class Auto extends Product {

    private Accessory accessory;
    private Body body;
    private Motor motor;

    public Auto(@NonNull Accessory accessory, @NonNull Body body, @NonNull Motor motor){
        super();
        this.accessory = accessory;
        this.body = body;
        this.motor = motor;
        log.info("Auto<" + this.getID() + "> (Body: <"+ body.getID() +">, Motor: <"+ motor.getID() +"> ,Accessory:<" + accessory.getID() + ">)");
    }

}
