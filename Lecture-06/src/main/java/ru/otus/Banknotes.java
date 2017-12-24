package ru.otus;

public class Banknotes {
    private int[] availableBanknotes = new int[] {10, 50, 100, 500, 1000, 5000};

    public boolean isAvailableNominal(int nominal) {
        for (int i = 0; i < availableBanknotes.length; i++) {
            if (availableBanknotes[i] == nominal)
                return true;
        }
        return false;
    }

    public boolean isAvailableNominalFormat(int money) {
        String moneyString = String.valueOf(money);
        return moneyString.charAt(moneyString.length()-1) != '0' ? false : true;
    }

}
