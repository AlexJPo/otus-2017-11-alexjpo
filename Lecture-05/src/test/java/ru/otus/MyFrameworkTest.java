package ru.otus;

import org.junit.After;
import org.junit.Test;
import ru.otus.annotations.Before;
import ru.otus.testclass.MyClass;
import ru.otus.testclass.MyTestClass;

import static org.junit.Assert.*;

public class MyFrameworkTest {
    private MyFramework myFramework;

    @Before
    public void before() throws Exception {
        myFramework = new MyFramework();
    }

    @Test
    public void runForClass() throws Exception {
        myFramework = new MyFramework();
        myFramework.runForClass(MyClass.class);
    }

    @After
    public void after() throws Exception {
        myFramework = null;
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