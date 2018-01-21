package ru.otus.testclass;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

import java.lang.reflect.Method;

public class TestSuite {
    private Object testClass = null;

    public void setTestClass(Object testClass) {
        this.testClass = testClass;
    }

    public void runTests() {
        try {
            Method[] classMethods = testClass.getClass().getDeclaredMethods();
            for (Method method: classMethods) {
                if (method.isAnnotationPresent(Before.class)) {
                    System.out.println("MyFramework: use Before");
                    System.out.println("MyFramework: call " + method.getName());
                    method.invoke(testClass);
                    System.out.println("---------------------------------");
                }

                if (method.isAnnotationPresent(Test.class)) {
                    System.out.println("MyFramework: use Test");
                    System.out.println("MyFramework: call " + method.getName());
                    method.invoke(testClass);
                    System.out.println("---------------------------------");
                }

                if (method.isAnnotationPresent(After.class)) {
                    System.out.println("MyFramework: use After");
                    System.out.println("MyFramework: call " + method.getName());
                    method.invoke(testClass);
                    System.out.println("---------------------------------");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
