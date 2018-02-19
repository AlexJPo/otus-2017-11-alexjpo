package ru.otus.orm.dataset;

import javax.persistence.*;

@Entity
@Table(name="otus_phone")
public class PhoneDataSet extends DataSet {
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserDataSet userId;

    @Column(name="number")
    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserDataSet getUserId() {
        return userId;
    }

    public void setUserId(UserDataSet userId) {
        this.userId = userId;
    }
}
