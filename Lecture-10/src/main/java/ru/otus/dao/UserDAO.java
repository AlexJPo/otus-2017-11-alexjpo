package ru.otus.dao;

import ru.otus.ConnectionHelper;
import ru.otus.dataset.UserDataSet;
import ru.otus.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDAO {
    private final Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public void save(UserDataSet dataSet) {
        try {
            Executor executor = new Executor(ConnectionHelper.getConnection());
            executor.save(dataSet);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public UserDataSet read(long id) {
        Executor executor = new Executor(connection);
        try {
            UserDataSet user = executor.load(id, UserDataSet.class);
            return user;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
