package com.lancao.practice.algorithms.leetcode;

import org.junit.jupiter.api.Test;

class MyCalendarThreeTest {

    @Test
    void book() {
        MyCalendarThree calendarThree = new MyCalendarThree();
        System.out.println(calendarThree.book(10, 20)); // returns 1
        System.out.println(calendarThree.book(50, 60)); // returns 1
        System.out.println(calendarThree.book(10, 40)); // returns 2
        System.out.println(calendarThree.book(5, 15)); // returns 3
        System.out.println(calendarThree.book(5, 10)); // returns 3
        System.out.println(calendarThree.book(25, 55)); // returns 3
    }
}