package ru.otus;

import java.util.HashMap;
import java.util.Map;

public class Transaction {

    public void abortTransaction(HashMap<Integer, Cell> banknotes) {
        for (Map.Entry<Integer, Cell> item : banknotes.entrySet()) {
            item.getValue().setReserved(0);
            banknotes.put(item.getKey(), item.getValue());
        }
    }

    public void applyTransaction(HashMap<Integer, Cell> banknotes) {
        for (Map.Entry<Integer, Cell> item : banknotes.entrySet()) {
            int banknoteCount = item.getValue().getAvailable() - item.getValue().getReserved();
            item.getValue().setAvailable(banknoteCount);
            item.getValue().setReserved(0);
            banknotes.put(item.getKey(), item.getValue());
        }
    }
}
