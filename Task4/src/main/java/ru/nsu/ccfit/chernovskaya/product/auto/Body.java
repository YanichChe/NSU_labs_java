package ru.nsu.ccfit.chernovskaya.product.auto;

import ru.nsu.ccfit.chernovskaya.product.Detail;

public class Body extends Detail {
    public Body(){
        super();
        logger.info("Body: <"+ this.getID() +">");
    }
}
