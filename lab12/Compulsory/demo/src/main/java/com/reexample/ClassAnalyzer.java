
package com.reexample;

import com.test.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ClassAnalyzer {

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("Usage: java ClassAnalyzer <full.class.Name>");
            return;
        }

        String className = args[0];
        Class<?> clazz = Class.forName(className);

        System.out.println("Class: " + clazz.getName());
        System.out.println("Methods:");

        Method[] methods = clazz.getDeclaredMethods();
        for (Method m : methods) {
            System.out.println(
                Modifier.toString(m.getModifiers()) + " " +
                m.getReturnType().getSimpleName() + " " +
                m.getName() + "(" +
                Arrays.stream(m.getParameterTypes())
                      .map(Class::getSimpleName)
                      .collect(Collectors.joining(", "))
                + ")"
            );
        }

        System.out.println("\nRunning @Test static no-arg methods:");

        for (Method m : methods) {
            if (Modifier.isStatic(m.getModifiers())
                && m.getParameterCount() == 0
                && m.isAnnotationPresent(Test.class)) {
                System.out.println("Running test: " + m.getName());
                try {
                    m.invoke(null);
                    System.out.println("Test passed");
                } catch (Exception e) {
                    System.out.println("Test failed: " + e.getCause());
                }
            }
        }
    }
}
