package ru.otus.dao;

import ru.otus.ConnectionHelper;
import ru.otus.executor.Executor;

import java.sql.Connection;

public class AddressDAO {
 /*   private final Connection connection;
/
    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public void saveAddress(UserDataSet dataSet) {
        try {
            Executor executor = new Executor(ConnectionHelper.getConnection());
            executor.saveAddress(dataSet);
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
    }*/
}
