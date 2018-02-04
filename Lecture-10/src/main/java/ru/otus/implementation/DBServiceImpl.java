package ru.otus.implementation;

import ru.otus.dao.AddressHibernateDAO;
import ru.otus.dao.UserDAO;
import ru.otus.dataset.AddressDataSet;
import ru.otus.dataset.PhoneDataSet;
import ru.otus.dataset.UserDataSet;
import ru.otus.interfaces.IDBService;

import java.sql.Connection;

public class DBServiceImpl implements IDBService {
    private final Connection connection;

    public DBServiceImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(UserDataSet dataSet) {
        UserDAO dao = new UserDAO(connection);
        dao.save(dataSet);
    }

    @Override
    public UserDataSet read(long id) {
        UserDAO dao = new UserDAO(connection);
        return dao.read(id);
    }

    @Override
    public void savePhone(PhoneDataSet dataSet) {

    }

    @Override
    public PhoneDataSet readPhone(long id) {
        return null;
    }

    @Override
    public void saveAddress(AddressDataSet dataSet) {

    }

    @Override
    public AddressDataSet readAddress(long id) {
        return null;
    }
}
