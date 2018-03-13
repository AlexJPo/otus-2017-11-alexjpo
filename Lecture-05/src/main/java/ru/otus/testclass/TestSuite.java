package ru.otus.testclass;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestSuite {
    private Object testClass = null;
    private List<Method> beforeMethods = new ArrayList<>();
    private List<Method> testMethods = new ArrayList<>();
    private List<Method> afterMethods = new ArrayList<>();

    public void setTestClass(Object testClass) {
        this.testClass = testClass;
    }

    public void runTests() {
        prepareTests();
        try {
            for (Method method : testMethods) {
                Object obj = testClass.getClass().newInstance();

                invokeBeforeMethods(obj);
                method.invoke(obj);
                invokeAfterMethod(obj);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void invokeAfterMethod(Object obj) throws IllegalAccessException, InvocationTargetException {
        for (Method beforeMethod : afterMethods) {
            beforeMethod.invoke(obj);
        }
    }

    private void invokeBeforeMethods(Object obj) throws IllegalAccessException, InvocationTargetException {
        for (Method beforeMethod : beforeMethods) {
            beforeMethod.invoke(obj);
        }
    }

    private void prepareTests() {
        if (testClass != null) {
            try {
                Method[] classMethods = testClass.getClass().getDeclaredMethods();
                for (Method method: classMethods) {
                    if (method.isAnnotationPresent(Before.class)) {
                        beforeMethods.add(method);
                    }

                    if (method.isAnnotationPresent(Test.class)) {
                        testMethods.add(method);
                    }

                    if (method.isAnnotationPresent(After.class)) {
                        afterMethods.add(method);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
