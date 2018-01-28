package ru.otus.dataset;

public class UserDataSet extends DataSet{
    private String name;
    private int age;

    public UserDataSet(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
