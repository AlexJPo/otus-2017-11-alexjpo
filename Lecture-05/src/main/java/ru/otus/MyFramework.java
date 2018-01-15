package ru.otus;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import ru.otus.annotations.*;
import java.lang.reflect.Method;

import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.util.Set;

public class MyFramework {
    private Reflections reflections;
    private URLClassLoader urlclassLoader;
    private ClassLoader classLoader;
    private Object myTestClass;
    private Object myAfterClass;
    private Object myBeforeClass;
    private Method[] classMethods;

    public MyFramework() {
    }

    public <T> MyFramework(Class<T> type) {
        runForClass(type);
    }

    public MyFramework(String type) {
        runForPackage(type);
    }

    public void runForPackage(String packageName) {
        initClassFinder(packageName);

        Set<Class<? extends Object>> allClasses = reflections.getSubTypesOf(Object.class);
        allClasses.forEach(this::runForClass);
    }

    private void initClassFinder(String packageName) {
        try {
            URL classesURL = Paths.get("target/test-classes").toUri().toURL();
            urlclassLoader = URLClassLoader.newInstance(new URL[]{classesURL},
                    ClasspathHelper.staticClassLoader());
            reflections = new Reflections(new ConfigurationBuilder()
                    .addUrls(ClasspathHelper.forPackage(packageName, urlclassLoader))
                    .addClassLoader(urlclassLoader)
                    .filterInputsBy(new FilterBuilder().includePackage(packageName))
                    .setScanners(
                            new SubTypesScanner(false),
                            new TypeAnnotationsScanner(),
                            new MethodAnnotationsScanner()));

            classLoader = new URLClassLoader(urlclassLoader.getURLs());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <T> void runForClass(Class<T> type) {
        try {
            System.out.println("MyFramework: runForClass()");

            if (!type.isAnnotation()) {
                iniBeforeClass(type);
                runBefore();

                iniAfterClass(type);
                runAfter();

                iniTestClass(type);
                runTest();
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private <T> void iniTestClass(Class<T> type) {
        try {
            if (myTestClass == null) {
                myTestClass = classLoader.loadClass(type.getName()).newInstance();
                classMethods = myTestClass.getClass().getDeclaredMethods();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private <T> void iniBeforeClass(Class<T> type) {
        try {
            myBeforeClass = classLoader.loadClass(type.getName()).newInstance();
            classMethods = myBeforeClass.getClass().getDeclaredMethods();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private <T> void iniAfterClass(Class<T> type) {
        try {
            myAfterClass = classLoader.loadClass(type.getName()).newInstance();
            classMethods = myAfterClass.getClass().getDeclaredMethods();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void runBefore() {
        try {
            for (Method method: classMethods) {
                if (method.isAnnotationPresent(Before.class)) {
                    System.out.println("MyFramework: use Before");
                    System.out.println("MyFramework: call " + method.getName());
                    method.invoke(myBeforeClass);
                    System.out.println("---------------------------------");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void runTest() {
        try {
            for (Method method : classMethods) {
                if (method.isAnnotationPresent(Test.class)) {
                    System.out.println("MyFramework: use Test");
                    System.out.println("MyFramework: call " + method.getName());
                    method.invoke(myTestClass);
                    System.out.println("---------------------------------");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void runAfter() {
        try {
            for (Method method : classMethods) {
                if (method.isAnnotationPresent(After.class)) {
                    System.out.println("MyFramework: use After");
                    System.out.println("MyFramework: call " + method.getName());
                    method.invoke(myAfterClass);
                    System.out.println("---------------------------------");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
