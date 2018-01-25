package ru.otus;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DepartmentTest {
    /*
    Написать приложение ATM Department:

    • Department может инициировать событие – восстановить состояние всех ATM до начального.
    (начальные состояния у разных ATM могут быть разными)
    * */

    private Department department;

    @Before
    public void init() {
         department = new Department();
    }

    @Test
    public void atmHasInitialState() {
        for (int i = 1; i < 3; i++) {
            Atm atm = new Atm();
            atm.setInitialBalance(i, 100);
            atm.deposit(i, 500);
            department.register(atm);
        }
        assertEquals(department.getAtms().size(), 2);
    }

    @Test
    public void resetAllAtmsToInitialState() {
        for (int i = 1; i < 4; i++) {
            Atm atm = new Atm();
            atm.setInitialBalance(i, 300);
            atm.deposit(i, 500);
            department.register(atm);
        }
        assertEquals(department.getAtms().size(), 3);

        int atmsBalanceBefore = department.getBalanceSum();
        assertEquals(atmsBalanceBefore, 4800);

        department.reset();
        int atmsBalanceAfter = department.getBalanceSum();
        assertEquals(atmsBalanceAfter, 1800);
    }

    @Test
    //Приложение может содержать несколько ATM
    public void shouldStoreDepartment() {
        for (int i = 1; i < 3; i++) {
            Atm atm = new Atm();
            atm.deposit(i, 500);
            department.register(atm);
        }
        assertEquals(department.getAtms().size(), 2);
    }

    @Test
    //Может собирать сумму остатков со всех ATM
    public void shouldReceiveSumBalanceFromAtms() {
        for (int i = 1; i < 3; i++) {
            Atm atm = new Atm();
            atm.deposit(i, 100);
            atm.deposit(i, 200);
            atm.deposit(i, 500);
            department.register(atm);
        }

        int balanceSumBefore = department.getBalanceSum();
        assertEquals(balanceSumBefore, 2400);

        department.getAtms().get(0).getMoney(200);
        department.getAtms().get(1).getMoney(100);
        int balanceSumAfter = department.getBalanceSum();
        assertEquals(balanceSumAfter, 2100);
    }
}