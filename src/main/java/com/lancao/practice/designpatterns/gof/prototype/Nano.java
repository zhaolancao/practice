package com.lancao.practice.designpatterns.gof.prototype;

import java.util.Random;

public class Nano extends BasicCar {
    public Nano(String modelName) {
        this.modelName = modelName;
        basePrice = 5000;
        onRoadPrice = basePrice + (new Random()).nextInt(1000);
    }

    public BasicCar clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
