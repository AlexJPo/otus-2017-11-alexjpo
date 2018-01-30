package ru.otus.command;

import java.util.*;

public class Atm implements ICommand {
    private HashMap<Integer, Integer> initialBalance = new HashMap<>();
    private HashMap<Integer, Integer> transaction = new HashMap<>();
    private Map<Integer, Integer> storage = new TreeMap(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    });

    @Override
    public int getBalance() {
        return storage.entrySet()
                .stream()
                .mapToInt(item->item.getKey() * item.getValue())
                .sum();
    }

    public void deposit(int count, int nominal) {
        int value = storage.getOrDefault(nominal, 0) + count;
        storage.put(nominal, value);
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
         if (initialBalance.isEmpty()) {
            initialBalance.put(amount, nominal);
            storage.put(amount, nominal);
        }
    }

    @Override
    public void reset() {
        storage.clear();
        if (!initialBalance.isEmpty()) {
            initialBalance.entrySet()
                    .forEach(item -> storage.put(item.getKey(), item.getValue()));
        }
    }
}
