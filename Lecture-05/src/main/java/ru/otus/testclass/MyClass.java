package ru.otus.testclass;

import ru.otus.annotations.*;
import java.util.HashMap;

public class MyClass {
    private int counter = 0;
    private String name;
    private HashMap<Integer, String> myMap = new HashMap<>();

    @Before
    public void setDefaultData() {
        myMap.put(counter, "Default data");
        counter++;
    }

    public void setData(String value) {
        myMap.put(counter, value);
        counter++;
    }

    @After
    public void printDefaultMap() {
        System.out.println(myMap.toString());
    }
}
