package ru.nsu.ccfit.chernovskaya.product.auto;

import lombok.extern.log4j.Log4j2;
import ru.nsu.ccfit.chernovskaya.product.Detail;

@Log4j2
public class Body extends Detail {
    public Body(){
        super();
        log.info("Body: <"+ this.getID() +">");
    }
}
