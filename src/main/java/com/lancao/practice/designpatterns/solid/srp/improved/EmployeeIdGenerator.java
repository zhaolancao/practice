package com.lancao.practice.designpatterns.solid.srp.improved;

import java.util.Random;

public class EmployeeIdGenerator {
    public String empId;

    public String generateEmpId(String empFirstName) {
        int random = new Random().nextInt(1000);
        empId = empFirstName.substring(0, 1) + random;
        return empId;
    }
}
