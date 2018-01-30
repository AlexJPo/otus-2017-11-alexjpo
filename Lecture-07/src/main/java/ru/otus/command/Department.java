package ru.otus.command;

import java.util.ArrayList;
import java.util.List;

public class Department  {
    private List<Atm> atms = new ArrayList<>();

    public void register(Atm atm) {
        atms.add(atm);
    }

    public List<Atm> getAtms() {
        return atms;
    }

    public int getBalanceSum() {
        int balance = 0;
        for (ICommand command : atms) {
            balance += command.getBalance();
        }
        return balance;
    }

    public void reset() {
        atms.forEach(ICommand::reset);
    }
}
