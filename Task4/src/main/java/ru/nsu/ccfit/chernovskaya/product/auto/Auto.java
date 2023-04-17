package ru.nsu.ccfit.chernovskaya.product.auto;

import com.sun.tools.javac.Main;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ru.nsu.ccfit.chernovskaya.product.Product;

@Getter
@Setter
@ToString

public class Auto extends Product {

    private Accessory accessory;
    private Body body;
    private Motor motor;

    public Auto(@NonNull Accessory accessory, @NonNull Body body, @NonNull Motor motor){
        super();
        this.accessory = accessory;
        this.body = body;
        this.motor = motor;
        logger.info("Auto<" + this.getID() + "> (Body: <"+ body.getID() +">, Motor: <"+ motor.getID() +"> ,Accessory:<" + accessory.getID() + ">)");
    }

}
