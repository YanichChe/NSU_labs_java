package ru.nsu.ccfit.chernovskaya.factory.product.auto;

import lombok.extern.log4j.Log4j2;
import ru.nsu.ccfit.chernovskaya.factory.product.Detail;

@Log4j2
public class Accessory extends Detail {
    public Accessory(){
        super();
        log.info("Accessory:<" + this.getID() + ">");
    }
}
