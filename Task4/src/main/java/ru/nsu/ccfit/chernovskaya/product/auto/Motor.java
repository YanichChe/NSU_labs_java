package ru.nsu.ccfit.chernovskaya.product.auto;

import ru.nsu.ccfit.chernovskaya.product.Detail;

public class Motor extends Detail {
    public Motor(){
        super();
        logger.info("Motor: <"+ this.getID() +">");
    }
}
