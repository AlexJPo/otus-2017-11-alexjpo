package ru.otus;

import ru.otus.annotations.*;
import java.util.HashMap;

public class MyClass {
    private int counter = 0;
    private String name;
    private HashMap<Integer, String> myMap = new HashMap<>();

    @Before
    @Test
    public void setDefaultData() {
        myMap.put(counter, "Default data");
        counter++;
    }

    @After
    @Test
    public void printDefaultMap() {
        System.out.println(myMap.toString());
    }
}
