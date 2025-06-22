package com.lancao.practice.designpatterns.solid.srp.initial;

import io.micrometer.common.util.StringUtils;

import java.util.Random;

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

    public String checkSeniority(double experienceInYears) {
        return experienceInYears > 5 ? "Senior" : "Junior";
    }

    public String generateEmpId(String empFirstName) {
        int random = new Random().nextInt(1000);
        empId = empFirstName.substring(0, 1) + random;
        return empId;
    }
}
