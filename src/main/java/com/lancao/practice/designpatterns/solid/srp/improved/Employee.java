package com.lancao.practice.designpatterns.solid.srp.improved;

public class Employee {
    public String firstName, lastName, empId;
    public double experienceInYears;

    public Employee(String firstName, String lastName, double experienceInYears) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.experienceInYears = experienceInYears;
    }

    public void displayEmpDetail() {
        System.out.println("Employee Name: " + lastName + " " + firstName);
        System.out.println("Employee has " + experienceInYears + " years of experience.");
    }
}
