package ru.otus;

import ru.otus.annotations.*;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

public class MyFramework {
    public static <T> void runForClass(Class<T> type) {
        try {
            if (!type.isAnnotation()) {
                Object myClass = Class.forName(type.getName()).newInstance();
                Method[] methods = myClass.getClass().getDeclaredMethods();

                System.out.println("MyFramework: runForClass()");
                runViaAnnotation(myClass, methods, Before.class);
                runViaAnnotation(myClass, methods, Test.class);
                runViaAnnotation(myClass, methods, After.class);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void runForPackage(String packageName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');
        try {
            Enumeration<URL> resources = classLoader.getResources(path);
            Set<File> dirs = new HashSet<>();
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                dirs.add(new File(resource.getFile()));
            }

            Set<Class> classes = new HashSet<>();
            for (File directory : dirs) {
                classes.addAll(findClasses(directory, packageName));
            }

            for (Class item : classes) { runForClass(item); }

            System.out.println();
        } catch (Exception ex) {
           throw new RuntimeException(ex);
        }
    }

    private static Set<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        Set<Class> classes = new HashSet<>();
        if (!directory.exists()) {
            return classes;
        }

        File[] files = directory.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(findClasses(file, packageName + "." + file.getName()));
            } else if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }

    private static void runViaAnnotation(Object myClass, Method[] methods, Class<? extends Annotation> annotationClass) {
        try {
            for (Method method : methods) {
                if (method.isAnnotationPresent(annotationClass)) {
                    System.out.println("MyFramework: use " + annotationClass.getSimpleName());
                    System.out.println("MyFramework: call " + method.getName());
                    method.invoke(myClass);
                    System.out.println("---------------------------------");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
