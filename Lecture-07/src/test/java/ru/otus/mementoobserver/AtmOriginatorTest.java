package ru.otus.mementoobserver;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AtmOriginatorTest {
    private AtmOriginator atmOriginator;

    @Before
    public void init() {
        atmOriginator = new AtmOriginator();
    }

    @Test
    //изначальный баланс
    public void atmHaveInitialState() {
        AtmOriginator atm = new AtmOriginator();
        atm.setInitialBalance(5, 500);

        InitialBalanceCaretaker initialBalanceCaretaker = new InitialBalanceCaretaker();
        initialBalanceCaretaker.setInitialBalance(atm.saveInitialBalance());
        InitialBalanceMemento initialBalanceMemento = initialBalanceCaretaker.getInitialBalance();

        assertEquals(initialBalanceMemento.getAmount(), 5);
        assertEquals(initialBalanceMemento.getNominal(), 500);
    }

    @Test
    //сброс к изначальному балансу
    public void resetAtmToInitialState() {
        AtmOriginator atm = new AtmOriginator();
        atm.setInitialBalance(5, 500);

        InitialBalanceCaretaker initialBalanceCaretaker = new InitialBalanceCaretaker();
        initialBalanceCaretaker.setInitialBalance(atm.saveInitialBalance());

        int initialBalance = atm.getBalance();
        assertEquals(initialBalance, 2500);

        atm.deposit(3, 200);
        int balance = atm.getBalance();
        assertEquals(balance, 3100);

        atm.reset(initialBalanceCaretaker.getInitialBalance());
        int balanceAfterReset = atm.getBalance();
        assertEquals(balanceAfterReset, 2500);
    }

    @Test
    //выдавать сумму остатка денежных средств
    public void getAtmBalance() {
        int balance = atmOriginator.getBalance();
        assertEquals(balance, 0);
    }

    @Test
    //принимать банкноты разных номиналов (на каждый номинал должна быть своя ячейка)
    public void putMoneyToAtmOriginator() {
        atmOriginator.deposit(5, 100);
        atmOriginator.deposit(1, 500);
        int balanceAfter = atmOriginator.getBalance();
        assertEquals(balanceAfter, 1000);
    }

    @Test(expected = RuntimeException.class)
    //выдавать ошибку если сумму нельзя выдать
    public void canNotReceiveMoneyOverLimit() {
        AtmOriginator atm = new AtmOriginator();
        atm.deposit(5, 100);

        atm.getMoney(1500);
        int balance = atm.getBalance();
    }

}