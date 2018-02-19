package ru.otus.orm.dao;

import org.hibernate.Session;
import ru.otus.orm.dataset.UserDataSet;

public class UserDAO {
    private Session session;

    public UserDAO(Session session) {
        this.session = session;
    }

    public void save(UserDataSet dataSet) {
        session.save(dataSet);
    }

    public UserDataSet read(long id) {
        return session.load(UserDataSet.class, id);
    }
}
