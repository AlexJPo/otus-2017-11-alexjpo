package ru.otus;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AtmTest {
    private Atm atm;

    @Before
    public void setUp() {
        atm = new Atm();
    }

    @Test
    public void availableNominal() {
        assertTrue(atm.isAvailableBanknote(10));
        assertTrue(atm.isAvailableBanknote(50));
        assertTrue(atm.isAvailableBanknote(100));
        assertTrue(atm.isAvailableBanknote(500));
        assertTrue(atm.isAvailableBanknote(1000));
        assertTrue(atm.isAvailableBanknote(5000));
    }

    @Test
    public void canReceiveBalance() {
        atm.depositMoney(100);
        int balanceOne = atm.getBalance();
        atm.depositMoney(100);
        int balanceTwo = atm.getBalance();

        assertNotEquals(0, balanceOne);
        assertNotEquals(0, balanceTwo);
        assertNotSame(balanceOne, balanceTwo);
    }

    @Test
    public void canPutMoneyPack() {
        atm.depositPackOfMoney(100, 3);
        atm.depositMoney(100);
        atm.depositPackOfMoney(500, 4);
        atm.depositMoney(500);
        int balance = atm.getBalance();

        assertNotEquals(0, balance);
    }

    @Test
    public void checkRightMoneyFormat() {
        assertTrue(atm.isAvailableMoneyFormat(100));
        assertTrue(atm.isAvailableMoneyFormat(150));
    }

    @Test
    public void checkWrongMoneyFormat() {
        assertFalse(atm.isAvailableMoneyFormat(155));
    }

    @Test
    public void hasEnoughBanknote() {
        atm.depositPackOfMoney(10, 3);
        atm.depositPackOfMoney(50, 3);
        atm.depositPackOfMoney(100, 3);

        assertTrue(atm.hasEnoughBanknote(10, 2));
    }

    @Test
    public void askPreviousCellAboutMoney() {
        atm.depositPackOfMoney(10, 10);
        atm.depositPackOfMoney(50, 2);
        atm.depositPackOfMoney(100, 1);
        int balance = atm.getBalance();
        System.out.println(balance);

        assertTrue(atm.findMoneyInPreviousCell(50, 120));
        balance = atm.getBalance();
        System.out.println(balance);

    }

    @Test
    public void getMoney() {
        atm.depositPackOfMoney(10, 30);
        atm.depositPackOfMoney(50, 2);
        atm.depositPackOfMoney(100, 1);
        int balance = atm.getBalance();
        System.out.println(balance);

        assertTrue(atm.getMoneyFromCell(240));
        balance = atm.getBalance();
        System.out.println(balance);
    }

    @Test
    public void canReceiveMoney() {
        atm.depositPackOfMoney(10, 6);
        atm.depositPackOfMoney(50, 3);
        atm.depositPackOfMoney(100, 3);

        int balance;

        assertTrue(atm.receiveMoney(80));
        balance = atm.getBalance();
        System.out.println(balance);

        assertTrue(atm.receiveMoney(150));
        balance = atm.getBalance();
        System.out.println(balance);

        assertTrue(atm.receiveMoney(30));
        balance = atm.getBalance();
        System.out.println(balance);
    }
}