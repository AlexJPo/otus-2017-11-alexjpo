package ru.otus;

import java.util.ArrayList;
import java.util.List;

public class Department implements IResetCommand {
    private List<Atm> atms = new ArrayList<>();

    public void register(Atm atm) {
        atms.add(atm);
    }

    public List<Atm> getAtms() {
        return atms;
    }

    public int getBalanceSum() {
        int balance = 0;
        for (IBalanceCommand command : atms) {
            balance += command.getBalance();
        }
        return balance;
    }

    @Override
    public void reset() {
        atms.forEach(IResetCommand::reset);
    }
}
