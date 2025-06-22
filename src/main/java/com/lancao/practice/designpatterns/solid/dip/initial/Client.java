package com.lancao.practice.designpatterns.solid.dip.initial;

public class Client {
    public static void main(String[] args) {
        System.out.println("***A demo without Dependency Inversion Principle.***");
        UserInterface ui = new UserInterface();
        ui.saveEmployeeId("001");
    }
}
