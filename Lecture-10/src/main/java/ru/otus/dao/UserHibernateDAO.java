package ru.otus.dao;

import org.hibernate.Session;
import ru.otus.dataset.UserDataSet;

public class UserHibernateDAO {
    private Session session;

    public UserHibernateDAO(Session session) {
        this.session = session;
    }

    public void save(UserDataSet dataSet) {
        session.save(dataSet);
    }

    public UserDataSet read(long id) {
        return session.load(UserDataSet.class, id);
    }
}
