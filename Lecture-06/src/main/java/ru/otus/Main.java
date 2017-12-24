package ru.otus;

public class Main {
    public static void main(String[] args) {
        Atm atm = new Atm();

        atm.depositPackOfMoney(10, 6);
        atm.depositPackOfMoney(50, 3);
        atm.depositPackOfMoney(100, 3);

        int balance;
        int receiveFirst = 80;
        int receiveSecond = 150;
        int receiveThird = 30;
        int receiveWrongNominal = 55;

        System.out.println("Попытка снять со счета: " + receiveFirst);
        atm.receiveMoney(receiveFirst);
        balance = atm.getBalance();
        System.out.println("Баланс: " + balance);

        System.out.println("Попытка снять со счета: " + receiveSecond);
        atm.receiveMoney(receiveSecond);
        balance = atm.getBalance();
        System.out.println("Баланс: " + balance);

        System.out.println("Попытка снять со счета: " + receiveWrongNominal);
        atm.receiveMoney(receiveWrongNominal);
        balance = atm.getBalance();
        System.out.println("Баланс: " + balance);

        System.out.println("Попытка снять со счета: " + receiveThird);
        atm.receiveMoney(receiveThird);
        balance = atm.getBalance();
        System.out.println("Баланс: " + balance);
    }
}
