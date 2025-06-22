package com.lancao.practice.designpatterns.solid.dip.improved;

public class Client {
    public static void main(String[] args) {
        System.out.println("***A demo that follows Dependency Inversion Principle.***");
        UserInterface ui = new UserInterface(new OracleDatabase());
        ui.saveEmployeeId("001");
        ui = new UserInterface(new MysqlDatabase());
        ui.saveEmployeeId("002");
    }
}
