package ru.otus;

import java.util.HashMap;
import java.util.Map;

public class BanknoteCells {
    private HashMap<Integer, Cell> cells = new HashMap<>();

    public HashMap<Integer,Cell> getCells() {
        return cells;
    }

    public int countMoney() {
        int currentBalance = 0;
        for (Map.Entry<Integer, Cell> item : cells.entrySet()) {
            currentBalance += item.getKey() * item.getValue().getAvailable();
        }
        return currentBalance;
    }

    public boolean getCellByNominal(int nominal) {
        return cells.containsKey(nominal);
    }

    public void addMoneyToCell(int money, int count) {
        cells.put(money, new Cell(count, 0));
    }

    public void replenishmentCell(int money, int count) {
        Cell currentCell = cells.get(money);
        currentCell.setAvailable(currentCell.getAvailable() + count);
        cells.put(money, currentCell);
    }

    public boolean isEnoughMoney(int key, int value) {
        Cell currentCell = cells.get(key);
        if (currentCell == null) {
            return false;
        } else {
            return currentCell.getAvailable() >= value ? true : false;
        }
    }

    public void reserveMoneyFromCellByIndex(int key, int value) {
        cells.get(key).setReserved(value);
    }

    public boolean isExistMoneyInCell(int key) {
        return cells.get(key).getAvailable() > 0;
    }

    public void reserveAllMoney(int key) {
        Cell currentCell = cells.get(key);
        currentCell.setReserved(currentCell.getAvailable());
    }

    public int getCellMoneyByIndex(int key) {
        return cells.get(key).getAvailable();
    }
}
