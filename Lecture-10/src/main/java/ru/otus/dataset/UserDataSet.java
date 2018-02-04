package ru.otus.dataset;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="otus_user")
public class UserDataSet extends DataSet {
    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @OneToOne(cascade = CascadeType.ALL)
    private AddressDataSet address;

    @OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
    private List<PhoneDataSet> phone;

    public List<PhoneDataSet> getPhone() {
        return phone;
    }

    public void setPhone(List<PhoneDataSet> phone) {
        this.phone = phone;
        for (PhoneDataSet item : phone) {
            item.setUserId(this);
        }
    }

    public AddressDataSet getAddress() {
        return address;
    }

    public void setAddress(AddressDataSet address) {
        this.address = address;
    }

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

    @Override
    public String toString() {
        return "UserDataSet{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", address=" + address +
                '}';
    }
}
