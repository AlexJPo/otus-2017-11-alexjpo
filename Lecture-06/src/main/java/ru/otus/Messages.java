package ru.otus;

public class Messages {
    public boolean notEnoughMoney() {
        System.out.println("Недостаточно средств для операции!");
        return false;
    }

    public boolean notAvailableBanknote() {
        System.out.println("Банкноты данного номинала недоступны!");
        return false;
    }

    public void wrongBanknoteNominal() {
        System.out.println("Неправильный тип банкноты, попробуйте другую!");
    }
}
