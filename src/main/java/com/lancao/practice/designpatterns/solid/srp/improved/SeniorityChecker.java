package com.lancao.practice.designpatterns.solid.srp.improved;

public class SeniorityChecker {
    public String checkSeniority(double experienceInYears) {
        return experienceInYears > 5 ? "Senior" : "Junior";
    }
}
