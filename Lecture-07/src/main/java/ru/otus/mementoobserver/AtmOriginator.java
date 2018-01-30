package ru.otus.mementoobserver;

import java.util.*;

public class AtmOriginator implements IObserved {
    private int initialAmount;
    private int initialNominal;

    private List<IObserver> observers = new ArrayList<>();

    private HashMap<Integer, Integer> transaction = new HashMap<>();
    private Map<Integer, Integer> storage = new TreeMap(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    });

    public void deposit(int count, int nominal) {
        int value = storage.getOrDefault(nominal, 0) + count;
        storage.put(nominal, value);
        notifyObservers();
    }

    public void getMoney(int amount) {
        for (Map.Entry<Integer, Integer> item : storage.entrySet()) {
            if (amount == 0) { break; }

            int remain = amount / item.getKey();
            if (item.getValue() >= remain) {
                transaction.put(item.getKey(), item.getValue() - remain);
                amount -= item.getKey() * remain;
            }
        }

        if (amount > 0) {
            throw new RuntimeException("Недостаточно средств");
        }

        for (Map.Entry<Integer, Integer> item : transaction.entrySet()) {
            for (Map.Entry<Integer, Integer> itemStore : storage.entrySet()) {
                if (itemStore.getKey().equals(item.getKey())) {
                    itemStore.setValue(item.getValue());
                    break;
                }
            }
        }

        transaction.clear();
    }

    public void setInitialBalance(int amount, int nominal) {
        if (initialAmount == 0 && initialNominal == 0) {
            initialAmount = amount;
            initialNominal = nominal;
            storage.put(amount, nominal);
            notifyObservers();
        }
    }

    public int getBalance() {
        return storage.entrySet()
                .stream()
                .mapToInt(item->item.getKey() * item.getValue())
                .sum();
    }

    public void reset(InitialBalanceMemento initialBalanceMemento) {
        initialAmount = initialBalanceMemento.getAmount();
        initialNominal = initialBalanceMemento.getNominal();

        storage.clear();
        storage.put(initialAmount, initialNominal);
        notifyObservers();
    }

    public InitialBalanceMemento saveInitialBalance() {
        return new InitialBalanceMemento(initialAmount, initialNominal);
    }

    @Override
    public void addObserver(IObserver o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(IObserver o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (IObserver o: observers) {
            o.notifyObserver(getBalance());
        }
    }
}
