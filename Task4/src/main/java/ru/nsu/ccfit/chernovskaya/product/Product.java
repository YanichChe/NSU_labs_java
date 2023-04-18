package ru.nsu.ccfit.chernovskaya.product;

import lombok.Getter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

@Getter
public abstract class Product {

    public static int global_ID = 0;
    private int ID;

    public Product(){
        ID = global_ID;
        global_ID++;
    }
}
