package ru.otus;

public class Atm {
    private int balance;

    private Messages messages = new Messages();
    private Banknotes banknotes = new Banknotes();
    private Transaction transaction = new Transaction();
    private BanknoteCells banknoteCells = new BanknoteCells();

    public int getBalance() {
        return banknoteCells.countMoney();
    }

    private void updateAtmBalance() {
        balance = banknoteCells.countMoney();
    }


    public boolean receiveMoney(int money) {
        if (money > balance) {
            return messages.notEnoughMoney();
        } else {
            if (isAvailableMoneyFormat(money)) {
                if (getMoneyFromCell(money)) {
                    transaction.applyTransaction(banknoteCells.getCells());
                    updateAtmBalance();
                    return true;
                } else {
                    transaction.abortTransaction(banknoteCells.getCells());
                }
            }
            return messages.notAvailableBanknote();
        }
    }

    public boolean findMoneyInPreviousCell(int key, int money) {
        if (key == 10) {
            int value = money / key;
            if (hasEnoughBanknote(key, value)) {
                banknoteCells.reserveMoneyFromCellByIndex(key, value);
                return true;
            }
        }

        if (key == 50) {
            int value = money / key;
            if (hasEnoughBanknote(key, value)) {
                banknoteCells.reserveMoneyFromCellByIndex(key, value);
                int remainMoney = money % key;

                if (remainMoney > 0) {
                    return getMoneyFromCell(remainMoney);
                }
                return true;
            } else {
                if (banknoteCells.isExistMoneyInCell(key)) {
                    banknoteCells.reserveAllMoney(key);
                    money -= key * banknoteCells.getCellMoneyByIndex(key);
                }
                return findMoneyInPreviousCell(10, money);
            }
        }

        if (key == 100) {
            int value = money / key;
            if (hasEnoughBanknote(key, value)) {
                banknoteCells.reserveMoneyFromCellByIndex(key, value);
                int remainMoney = money % key;

                if (remainMoney > 0) {
                    return getMoneyFromCell(remainMoney);
                }
                return true;
            } else {
                if (banknoteCells.isExistMoneyInCell(key)) {
                    banknoteCells.reserveAllMoney(key);
                    money -= key * banknoteCells.getCellMoneyByIndex(key);
                }
                return findMoneyInPreviousCell(50, money);
            }
        }

        if (key == 500) {
            int value = money / key;
            if (hasEnoughBanknote(key, value)) {
                banknoteCells.reserveMoneyFromCellByIndex(key, value);
                int remainMoney = money % key;

                if (remainMoney > 0) {
                    return getMoneyFromCell(remainMoney);
                }
                return true;
            } else {
                if (banknoteCells.isExistMoneyInCell(key)) {
                    banknoteCells.reserveAllMoney(key);
                    money -= key * banknoteCells.getCellMoneyByIndex(key);
                }
                return findMoneyInPreviousCell(100, money);
            }
        }

        if (key == 1000) {
            int value = money / key;
            if (hasEnoughBanknote(key, value)) {
                banknoteCells.reserveMoneyFromCellByIndex(key, value);
                int remainMoney = money % key;

                if (remainMoney > 0) {
                    return getMoneyFromCell(remainMoney);
                }
                return true;
            } else {
                if (banknoteCells.isExistMoneyInCell(key)) {
                    banknoteCells.reserveAllMoney(key);
                    money -= key * banknoteCells.getCellMoneyByIndex(key);
                }
                return findMoneyInPreviousCell(500, money);
            }
        }

        if (key == 5000) {
            int value = money / key;
            if (hasEnoughBanknote(key, value)) {
                banknoteCells.reserveMoneyFromCellByIndex(key, value);
                int remainMoney = money % key;

                if (remainMoney > 0) {
                    return getMoneyFromCell(remainMoney);
                }
                return true;
            } else {
                if (banknoteCells.isExistMoneyInCell(key)) {
                    banknoteCells.reserveAllMoney(key);
                    money -= key * banknoteCells.getCellMoneyByIndex(key);
                }
                return findMoneyInPreviousCell(1000, money);
            }
        }

        return false;
    }

    public boolean getMoneyFromCell(int money) {
        int value;

        if (money >= 10 && money < 50) {
            value = money / 10;
            if (hasEnoughBanknote(10, value)) {
                banknoteCells.reserveMoneyFromCellByIndex(10, value);
                return true;
            }
            return false;
        }

        if (money >= 50 && money < 100) {
            value = money / 50;
            return isEnoughMoneyInPrevCell(50, 10, value, money);
        }

        if (money >= 100 && money < 500) {
            value = money / 100;
            return isEnoughMoneyInPrevCell(100, 50, value, money);
        }

        if (money >= 500 && money < 1000) {
            value = money / 500;
            return isEnoughMoneyInPrevCell(500, 100, value, money);
        }

        if (money >= 1000 && money < 5000) {
            value = money / 1000;
            return isEnoughMoneyInPrevCell(1000, 500, value, money);
        }

        if (money >= 5000) {
            value = money / 5000;
            return isEnoughMoneyInPrevCell(5000, 1000, value, money);
        }

        return false;
    }

    private boolean isEnoughMoneyInPrevCell(int currentCell, int previousCell, int value, int money) {
        if (hasEnoughBanknote(currentCell, value)) {
            banknoteCells.reserveMoneyFromCellByIndex(currentCell, value);
            int remainMoney = money % currentCell;

            if (remainMoney > 0) {
                return getMoneyFromCell(remainMoney);
            }
            return true;
        } else {
            if (banknoteCells.isExistMoneyInCell(currentCell)) {
                banknoteCells.reserveAllMoney(currentCell);
                money -= currentCell * banknoteCells.getCellMoneyByIndex(currentCell);
            }
            return findMoneyInPreviousCell(previousCell, money);
        }
    }


    public void depositMoney(int money) {
        if (isAvailableBanknote(money)) {
            if (banknoteCells.getCellByNominal(money)) {
                banknoteCells.replenishmentCell(money, 1);
            } else {
                banknoteCells.addMoneyToCell(money, 1);
            }
            updateAtmBalance();
        } else {
            messages.wrongBanknoteNominal();
        }
    }

    public void depositPackOfMoney(int money, int count) {
        if (isAvailableBanknote(money)) {
            if (banknoteCells.getCellByNominal(money)) {
                banknoteCells.replenishmentCell(money, count);
            } else {
                banknoteCells.addMoneyToCell(money, count);
            }
            updateAtmBalance();
        } else {
            messages.wrongBanknoteNominal();
        }
    }


    public boolean isAvailableMoneyFormat(int money) {
        return banknotes.isAvailableNominalFormat(money);
    }

    public boolean isAvailableBanknote(int nominal) {
        return banknotes.isAvailableNominal(nominal);
    }

    public boolean hasEnoughBanknote(int key, int value) {
        return banknoteCells.isEnoughMoney(key, value);
    }
}
