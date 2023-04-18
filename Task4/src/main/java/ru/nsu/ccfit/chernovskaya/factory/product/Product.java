package ru.nsu.ccfit.chernovskaya.factory.product;

import lombok.Getter;

@Getter
public abstract class Product {

    public static int global_ID = 0;
    private int ID;

    public Product(){
        ID = global_ID;
        global_ID++;
    }
}
