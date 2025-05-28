package com.example;

import com.test.Test;

public class SomeTestClass {

    @Test
    public static void testOne() {
        System.out.println("TestOne running");
    }

    @Test
    public static void testTwo() {
        System.out.println("TestTwo running");
        if (1 + 1 != 2) {
            throw new RuntimeException("Math error!");
        }
    }

    public static void notATest() {
        System.out.println("Not a test method");
    }
}
