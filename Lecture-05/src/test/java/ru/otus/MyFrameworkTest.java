package ru.otus;

import org.junit.Test;
import ru.otus.helper.FindClass;
import ru.otus.testclass.MyTestClass;

import static org.junit.Assert.*;

public class MyFrameworkTest {


    public void runForPackage() throws Exception {
        MyFramework myFramework = new MyFramework();
        myFramework.runForPackage("ru.otus");
    }

    public void mySecondClassTest() throws  Exception {
        MyTestClass myTestClass = new MyTestClass();
        assertEquals(3, myTestClass.sum(2, 1));
    }

    @Test
    public void classFounder() throws  Exception {
        FindClass findClass = new FindClass();
        findClass.getClassFromPackage("ru.otus");
    }
}