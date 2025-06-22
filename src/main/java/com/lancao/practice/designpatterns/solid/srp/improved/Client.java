package com.lancao.practice.designpatterns.solid.srp.improved;

public class Client {
    public static void main(String[] args) {
        System.out.println("*** A demo that follows SRP. ***");
        Employee robin = new Employee("Robin", "Smith", 7.5);
        Employee kevin = new Employee("Kevin", "Proctor", 3.2);
        showEmpDetail(robin);
        System.out.println("\n******\n");
        showEmpDetail(kevin);
    }

    private static void showEmpDetail(Employee emp) {
        // display employee detail
        emp.displayEmpDetail();
        // generate employee ID
        EmployeeIdGenerator idGenerator = new EmployeeIdGenerator();
        System.out.println("Employee Id: " + idGenerator.generateEmpId(emp.firstName));
        // check seniority level
        SeniorityChecker seniorityChecker = new SeniorityChecker();
        System.out.println("This employee is a " + seniorityChecker.checkSeniority(emp.experienceInYears) + " employee.");
    }
}
