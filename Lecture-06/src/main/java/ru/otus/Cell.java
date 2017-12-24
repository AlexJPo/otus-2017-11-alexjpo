package ru.otus;

public class Cell {
    private int available;
    private int reserved;

    public Cell(int available, int reserved) {
        this.available = available;
        this.reserved = reserved;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getReserved() {
        return reserved;
    }

    public void setReserved(int reserved) {
        this.reserved = reserved;
    }
}
