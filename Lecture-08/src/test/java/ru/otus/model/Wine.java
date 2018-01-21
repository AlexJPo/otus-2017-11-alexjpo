package ru.otus.model;

public class Wine {
    private String name;
    private Object[] ratings;

    public Object[] getRatings() {
        return ratings;
    }

    public void setRatings(Object[] ratings) {
        this.ratings = ratings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
