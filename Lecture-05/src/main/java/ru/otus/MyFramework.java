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
    private Object myClass;
    private ClassLoader classLoader;
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <T> void runForClass(Class<T> type) {
        try {
            System.out.println("MyFramework: runForClass()");

            initClassForTest(type);
            runBefore(type);
            runTest(type);
            runAfter(type);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private <T> void initClassForTest(Class<T> type) {
        try {
            classLoader = new URLClassLoader(urlclassLoader.getURLs());
            myClass = classLoader.loadClass(type.getName()).newInstance();
            classMethods = myClass.getClass().getDeclaredMethods();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private <T> void runBefore(Class<T> type) {
        try {
            if (!type.isAnnotation()) {
                for (Method method: classMethods) {
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
                for (Method method : classMethods) {
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
                for (Method method : classMethods) {
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
