package com.lancao.practice.designpatterns.gof.prototype;

public class Client {
    public static void main(String[] args) throws CloneNotSupportedException {
        System.out.println("*** Prototype Pattern Demo. ***");
        BasicCar nano = new Nano("Nano XM624 cc");
        System.out.println(nano);
        System.out.println("----------");
        // Get a cloned version of Nano
        BasicCar clonedNano = nano.clone();
        printCarDetail(clonedNano);
    }

    private static void printCarDetail(BasicCar car) {
        System.out.println("Editing a cloned model: ");
        System.out.println("Mode: " + car.getModelName());
        car.setOnRoadPrice(car.getOnRoadPrice() + 100);
        System.out.println("It's on-road price: $" + car.getOnRoadPrice());
    }
}
