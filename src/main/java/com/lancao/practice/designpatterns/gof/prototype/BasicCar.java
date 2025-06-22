package com.lancao.practice.designpatterns.gof.prototype;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BasicCar implements Cloneable {
    protected String modelName;
    protected int basePrice = 0, onRoadPrice = 0;

    public BasicCar clone() throws CloneNotSupportedException {
        return (BasicCar) super.clone();
    }
}
