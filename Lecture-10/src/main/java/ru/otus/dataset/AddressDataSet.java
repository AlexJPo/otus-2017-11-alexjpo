package ru.otus.dataset;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Table;

@Entity
@Table(name = "otus_adress")
public class AddressDataSet extends DataSet {
    @Column(name="street")
    private String street;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
