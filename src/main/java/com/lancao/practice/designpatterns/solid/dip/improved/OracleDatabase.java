package com.lancao.practice.designpatterns.solid.dip.improved;

public class OracleDatabase implements Database{
    @Override
    public void saveEmpIdInDatabase(String empId) {
        System.out.println("Employee Id: " + empId + " is saved into Oracle.");
    }
}
