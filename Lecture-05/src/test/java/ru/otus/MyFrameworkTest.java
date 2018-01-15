package ru.otus;

import org.junit.Test;
import ru.otus.annotations.Before;
import ru.otus.testclass.MyTestClass;

import static org.junit.Assert.*;

public class MyFrameworkTest {


    @Before
    public void runForPackage() throws Exception {
        MyFramework myFramework = new MyFramework();
        myFramework.runForPackage("ru.otus");
    }

    @Test
    public void mySecondClassTest() throws  Exception {
        MyTestClass myTestClass = new MyTestClass();
        assertEquals(3, myTestClass.sum(2, 1));
    }
}