package com.lancao.practice.designpatterns.solid.dip.improved;

public class UserInterface {
    private Database database;

    public UserInterface(Database database) {
        this.database = database;
    }

    public void saveEmployeeId(String empId) {
        database.saveEmpIdInDatabase(empId);
    }
}
