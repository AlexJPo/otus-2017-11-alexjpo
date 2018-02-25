package ru.otus.orm.dao;

import org.hibernate.Session;
import ru.otus.orm.dataset.AddressDataSet;

public class AddressDAO {
    private Session session;

    public AddressDAO(Session session) {
        this.session = session;
    }

    public void save(AddressDataSet dataSet) {
        session.save(dataSet);
    }

    public AddressDataSet read(long id) {
        return session.load(AddressDataSet.class, id);
    }
}
