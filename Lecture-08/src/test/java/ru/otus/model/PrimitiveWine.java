package ru.otus.model;

public class PrimitiveWine {
    private float strength;
    private double price;
    private int balance;
    private boolean available;
    private int[] capacity;
    private String[] grapes;
    private Wine wine;
    private Wine[] wines;

    public Wine[] getWines() {
        return wines;
    }

    public void setWines(Wine[] wines) {
        this.wines = wines;
    }

    public Wine getWine() {
        return wine;
    }

    public void setWine(Wine wine) {
        this.wine = wine;
    }

    public String[] getGrapes() {
        return grapes;
    }

    public void setGrapes(String[] grapes) {
        this.grapes = grapes;
    }

    public int[] getCapacity() {
        return capacity;
    }

    public void setCapacity(int[] capacity) {
        this.capacity = capacity;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public float getStrength() {
        return strength;
    }

    public void setStrength(float strength) {
        this.strength = strength;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
