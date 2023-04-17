package ru.nsu.ccfit.chernovskaya.product.auto;

import ru.nsu.ccfit.chernovskaya.product.Detail;

public class Accessory extends Detail {
    public Accessory(){
        super();
        logger.info("Accessory:<" + this.getID() + ">");
    }
}
