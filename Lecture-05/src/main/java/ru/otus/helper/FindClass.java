package ru.otus.helper;

import java.io.File;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

public class FindClass {
    public Set<Class> getClassFromPackage(String packageName) {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        String path = packageName.replace('.', '/');

        try {
            Package[] pa = Package.getPackages();
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
