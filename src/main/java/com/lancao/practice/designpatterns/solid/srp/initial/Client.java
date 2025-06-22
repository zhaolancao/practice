package com.lancao.practice.designpatterns.solid.srp.initial;

public class Client {
    public static void main(String[] args) {
        System.out.println("*** A demo without SRP. ***");
        Employee robin = new Employee("Robin", "Smith", 7.5);
        Employee kevin = new Employee("Kevin", "Proctor", 3.2);
        showEmpDetail(robin);
        System.out.println("\n******\n");
        showEmpDetail(kevin);
    }

    private static void showEmpDetail(Employee emp) {
        emp.displayEmpDetail();
        System.out.println("Employee Id: " + emp.generateEmpId(emp.firstName));
        System.out.println("This employee is a " + emp.checkSeniority(emp.experienceInYears) + " employee.");
    }
}
