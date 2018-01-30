package ru.otus.mementoobserver;

public class InitialBalanceMemento {
    private final int nominal;
    private final int amount;

    public InitialBalanceMemento(int amount, int nominal) {
        this.nominal = nominal;
        this.amount = amount;
    }

    public int getNominal() {
        return nominal;
    }

    public int getAmount() {
        return amount;
    }
}
