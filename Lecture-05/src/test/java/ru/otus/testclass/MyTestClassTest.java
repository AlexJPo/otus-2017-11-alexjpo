package ru.otus.testclass;


import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

public class MyTestClassTest {
    @Before
    public void sum() throws Exception {
        System.out.println("Test from test");
    }
}