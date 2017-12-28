package ru.otus.helper;


import static org.reflections.ReflectionUtils.*;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import ru.otus.MyFramework;
import ru.otus.annotations.Test;
import ru.otus.testclass.MyTestClass;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Paths;
import java.util.*;

public class FindClass {
    public Set<Class> getClassFromPackage(String packageName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');

        try {
            URL testClassesURL = Paths.get("target/test-classes").toUri().toURL();

            URLClassLoader urlclassLoader = URLClassLoader.newInstance(new URL[]{testClassesURL},
                    ClasspathHelper.staticClassLoader());

            Reflections reflections = new Reflections(new ConfigurationBuilder()
                    .addUrls(ClasspathHelper.forPackage(packageName, urlclassLoader))
                    .addClassLoader(urlclassLoader)
                    .filterInputsBy(new FilterBuilder().includePackage(packageName))
                    .setScanners(
                            new SubTypesScanner(false),
                            new TypeAnnotationsScanner(),
                            new MethodAnnotationsScanner()));

            /*Reflections reflections = new Reflections(ClasspathHelper.forPackage(packageName),
                    new SubTypesScanner(false));*/

            Set<Class<? extends Object>> allClasses = reflections.getSubTypesOf(Object.class);

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

            classes.addAll(allClasses);

            return classes;
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private Set<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
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
}
