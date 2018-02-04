package ru.otus.executor;

import ru.otus.dataset.DataSet;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Executor {
    private final Connection connection;
    private final String INSERT_USER = "INSERT INTO otus_user (name, age) VALUES (?,?);";
    private final String SELECT_USER = "SELECT * FROM otus_user WHERE id = ?";

    private StringBuilder stringBuilder = new StringBuilder();

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public <T extends DataSet> void save(T user) throws SQLException, IllegalAccessException {
        Class clazz = user.getClass();
        Field[] fields = clazz.getDeclaredFields();

        stringBuilder.append("Save user: ");

        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = field.get(user);

                switch (field.getName()) {
                    case "name":
                        preparedStatement.setObject(1, value);
                        stringBuilder.append("name=");
                        stringBuilder.append(value);
                        break;
                    case "age":
                        preparedStatement.setObject(2, value);
                        stringBuilder.append("age=");
                        stringBuilder.append(value);
                        break;
                }
                field.setAccessible(false);
            }

            System.out.println(stringBuilder.toString());
            preparedStatement.executeUpdate();
        }
        stringBuilder.setLength(0);
    }

    public <T extends DataSet> T load(long id, Class<T> clazz) throws Exception {
        try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER)) {
            preparedStatement.setLong(1, id);
            Object object;

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                object = clazz.newInstance();
                ((T)object).setId(id);

                if (!resultSet.isBeforeFirst()) { return null; }

                Field[] fields = clazz.getDeclaredFields();
                while (resultSet.next()) {
                    for (Field field : fields) {
                        field.setAccessible(true);

                        switch (field.getName()) {
                            case "name":
                                stringBuilder.append("name=");
                                stringBuilder.append(resultSet.getString("name"));
                                field.set(object, resultSet.getObject("name"));
                                break;
                            case "age":
                                stringBuilder.append("age=");
                                stringBuilder.append(resultSet.getInt("age"));
                                field.set(object, resultSet.getInt("age"));
                                break;
                        }
                        field.setAccessible(false);
                    }
                }

                System.out.println(stringBuilder.toString());
                stringBuilder.setLength(0);
            }

            return (T) object;
        }
    }
}
