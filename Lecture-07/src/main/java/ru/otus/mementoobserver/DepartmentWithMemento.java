package ru.otus.mementoobserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DepartmentWithMemento implements IObserver {
    private int totalBalance = 0;
    private List<AtmOriginator> atms = new ArrayList<>();
    private List<InitialBalanceCaretaker> atmsBalance = new ArrayList<>();

    public void register(AtmOriginator atm, InitialBalanceCaretaker initialBalanceCaretaker) {
        atms.add(atm);
        atmsBalance.add(initialBalanceCaretaker);
    }

    public List<AtmOriginator> getAtms() {
        return atms;
    }

    public int getBalanceSum() {
        totalBalance = 0;
        atms.forEach(IObserved::notifyObservers);
        return totalBalance;
    }

    public void reset() {
        int i = 0;
        for (AtmOriginator item : atms) {
            item.reset(atmsBalance.get(i).getInitialBalance());
            i++;
        }
    }

    @Override
    public void notifyObserver(int balance) {
        totalBalance += balance;
    }
}
