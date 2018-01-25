package ru.otus;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AtmTest {
    private Atm atm;

    @Before
    public void init() {
        atm = new Atm();
    }

    @Test
    //изначальный баланс
    public void atmHaveInitialState() {
        Atm atm = new Atm();
        atm.setInitialBalance(5, 500);
        int balance = atm.getBalance();
        assertEquals(balance, 2500);
    }

    @Test
    //сброс к изначальному балансу
    public void resetAtmToInitialState() {
        Atm atm = new Atm();
        atm.setInitialBalance(5, 500);

        int initialBalance = atm.getBalance();
        assertEquals(initialBalance, 2500);

        atm.deposit(3, 200);
        int balance = atm.getBalance();
        assertEquals(balance, 3100);

        atm.reset();
        int balanceAfterReset = atm.getBalance();
        assertEquals(balanceAfterReset, 2500);
    }

    @Test
    //выдавать сумму остатка денежных средств
    public void getAtmBalance() {
        int balance = atm.getBalance();
        assertEquals(balance, 0);
    }

    @Test
    //принимать банкноты разных номиналов (на каждый номинал должна быть своя ячейка)
    public void putMoneyToAtm() {
        atm.deposit(5, 100);
        atm.deposit(1, 500);
        int balanceAfter = atm.getBalance();
        assertEquals(balanceAfter, 1000);
    }

    @Test
    //выдавать запрошенную сумму минимальным количеством банкнот
    public void canReceiveMoneyByMinimalNominal() {
        atm.deposit(10, 500);
        atm.deposit(10, 50);
        atm.deposit(10, 100);

        atm.getMoney(0);
        int balance = atm.getBalance();
        assertEquals(balance, 6500);
    }

    @Test(expected = RuntimeException.class)
    //выдавать ошибку если сумму нельзя выдать
    public void canNotReceiveMoneyOverLimit() {
        Atm atm = new Atm();
        atm.deposit(5, 100);

        atm.getMoney(1500);
        int balance = atm.getBalance();
    }
}