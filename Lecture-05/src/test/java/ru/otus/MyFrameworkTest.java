package ru.otus;

import org.junit.Test;
import ru.otus.testclass.MyClass;
import ru.otus.testclass.MyTestClass;

import static org.junit.Assert.*;

public class MyFrameworkTest {

    @Test
    public void runForClass() throws Exception {
        MyFramework myFramework = new MyFramework();
        myFramework.runForClass(MyClass.class);
    }

    @Test
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