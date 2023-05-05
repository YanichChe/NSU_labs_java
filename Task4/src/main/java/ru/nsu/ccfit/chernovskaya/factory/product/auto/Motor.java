package ru.nsu.ccfit.chernovskaya.factory.product.auto;

import lombok.extern.log4j.Log4j2;
import ru.nsu.ccfit.chernovskaya.factory.product.Detail;

@Log4j2
public class Motor extends Detail {
    public Motor(){
        super();
        log.info("Motor: <"+ this.getID() +">");
    }
}
