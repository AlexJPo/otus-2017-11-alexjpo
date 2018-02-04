package ru.otus.dao;

import org.hibernate.Session;
import ru.otus.dataset.PhoneDataSet;

public class PhoneDAO {
    private Session session;

    public PhoneDAO(Session session) {
        this.session = session;
    }

    public void save(PhoneDataSet dataSet) {
        session.save(dataSet);
    }

    public PhoneDataSet read(long id) {
        return session.load(PhoneDataSet.class, id);
    }
}
