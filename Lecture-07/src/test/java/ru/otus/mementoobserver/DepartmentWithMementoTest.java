package ru.otus.mementoobserver;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DepartmentWithMementoTest {
    private DepartmentWithMemento department;

    @Before
    public void init() {
        department = new DepartmentWithMemento();
    }

    @Test
    public void atmHasInitialState() {
        DepartmentWithMemento department = new DepartmentWithMemento();
        for (int i = 1; i < 3; i++) {
            AtmOriginator atm = new AtmOriginator();
            atm.setInitialBalance(i, 100);
            atm.deposit(i, 500);

            InitialBalanceCaretaker initialBalanceCaretaker = new InitialBalanceCaretaker();
            initialBalanceCaretaker.setInitialBalance(atm.saveInitialBalance());

            department.register(atm, initialBalanceCaretaker);
        }
        assertEquals(department.getAtms().size(), 2);
    }

    @Test
    //может инициировать событие – восстановить состояние всех ATM до начального
    public void resetAllAtmsToInitialState() {
        for (int i = 1; i < 4; i++) {
            AtmOriginator atm = new AtmOriginator();
            atm.addObserver(department);
            atm.setInitialBalance(i, 300);
            atm.deposit(i, 500);

            InitialBalanceCaretaker initialBalanceCaretaker = new InitialBalanceCaretaker();
            initialBalanceCaretaker.setInitialBalance(atm.saveInitialBalance());

            department.register(atm, initialBalanceCaretaker);
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
            AtmOriginator atm = new AtmOriginator();
            atm.deposit(i, 500);
            department.register(atm, new InitialBalanceCaretaker());
        }
        assertEquals(department.getAtms().size(), 2);
    }

    @Test
    //Может собирать сумму остатков со всех ATM
    public void shouldReceiveSumBalanceFromAtms() {
        for (int i = 1; i < 3; i++) {
            AtmOriginator atm = new AtmOriginator();
            atm.addObserver(department);
            atm.deposit(i, 100);
            atm.deposit(i, 200);
            atm.deposit(i, 500);
            department.register(atm, new InitialBalanceCaretaker());
        }

        int balanceSumBefore = department.getBalanceSum();
        assertEquals(balanceSumBefore, 2400);

        department.getAtms().get(0).getMoney(200);
        department.getAtms().get(1).getMoney(100);
        int balanceSumAfter = department.getBalanceSum();
        assertEquals(balanceSumAfter, 2100);
    }

}