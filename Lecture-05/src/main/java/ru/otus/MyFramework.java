package ru.otus;

import ru.otus.annotations.*;
import ru.otus.helper.FindClass;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Set;

public class MyFramework {
    private FindClass findClass = new FindClass();

    public MyFramework() {
    }

    public <T> MyFramework(Class<T> type) {
        runForClass(type);
    }

    public MyFramework(String type) {
        runForPackage(type);
    }

    public void runForPackage(String type) {
        Set<Class> classes = findClass.getClassFromPackage(type);
        for (Class item : classes) { runForClass(item); }
    }

    public <T> void runForClass(Class<T> type) {
        try {
            System.out.println("MyFramework: runForClass()");
            runBefore(type);
            runTest(type);
            runAfter(type);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private <T> void runBefore(Class<T> type) {
        try {
            if (!type.isAnnotation()) {
                Object myClass = Class.forName(type.getName()).newInstance();
                Method[] methods = myClass.getClass().getDeclaredMethods();

                for (Method method : methods) {
                    if (method.isAnnotationPresent(Before.class)) {
                        System.out.println("MyFramework: use Before");
                        System.out.println("MyFramework: call " + method.getName());
                        method.invoke(myClass);
                        System.out.println("---------------------------------");
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private <T> void runTest(Class<T> type) {
        try {
            if (!type.isAnnotation()) {
                Object myClass = Class.forName(type.getName()).newInstance();
                Method[] methods = myClass.getClass().getDeclaredMethods();

                for (Method method : methods) {
                    if (method.isAnnotationPresent(Test.class)) {
                        System.out.println("MyFramework: use Test");
                        System.out.println("MyFramework: call " + method.getName());
                        method.invoke(myClass);
                        System.out.println("---------------------------------");
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private <T> void runAfter(Class<T> type) {
        try {
            if (!type.isAnnotation()) {
                Object myClass = Class.forName(type.getName()).newInstance();
                Method[] methods = myClass.getClass().getDeclaredMethods();

                for (Method method : methods) {
                    if (method.isAnnotationPresent(After.class)) {
                        System.out.println("MyFramework: use After");
                        System.out.println("MyFramework: call " + method.getName());
                        method.invoke(myClass);
                        System.out.println("---------------------------------");
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
