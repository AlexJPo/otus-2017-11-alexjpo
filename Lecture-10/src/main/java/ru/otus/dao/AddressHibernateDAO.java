package ru.otus.dao;

import org.hibernate.Session;
import ru.otus.dataset.AddressDataSet;

public class AddressHibernateDAO {
    private Session session;

    public AddressHibernateDAO(Session session) {
        this.session = session;
    }

    public void save(AddressDataSet dataSet) {
        session.save(dataSet);
    }

    public AddressDataSet read(long id) {
        return session.load(AddressDataSet.class, id);
    }
}
