package ru.otus.implementation;

import org.junit.Test;

import ru.otus.ConnectionHelper;
import ru.otus.dataset.AddressDataSet;
import ru.otus.dataset.UserDataSet;

import static org.junit.Assert.*;

public class DBServiceImplTest {
    /**
    *   ДЗ-10: Hibernate ORM

        На основе предыдущего ДЗ (myORM):
        + 1. Оформить решение в виде DBService (
            + interface DBService,
            + class DBServiceImpl,
            + UsersDAO,
            + UsersDataSet,
            + Executor)
        + 2. Не меняя интерфейс DBService сделать DBServiceHibernateImpl на Hibernate.
        + 3. Добавить в UsersDataSet поля:
            адресс (OneToOne)
            class AddressDataSet{
                private String street;
            }
            и телефон* (OneToMany)
            class PhoneDataSet{
                private String number;
            }
            Добавить соответствущие датасеты и DAO.

        4.** Поддержать работу из пункта (3) в myORM

    */

    //@Test
    public void serviceShouldSave() throws Exception {
        DBServiceImpl dbService = new DBServiceImpl(ConnectionHelper.getConnection());
        UserDataSet userDataSet = new UserDataSet();
        userDataSet.setName("user3");
        userDataSet.setAge(15);

        dbService.save(userDataSet);
    }

    @Test
    public void serviceShouldRead() throws Exception {
        DBServiceImpl dbService = new DBServiceImpl(ConnectionHelper.getConnection());
        UserDataSet user = dbService.read(1);

        assertEquals(user.getName(), "user1");
        assertEquals(user.getAge(), 15);

    }

    @Test
    public void notUserFind() throws Exception {
        DBServiceImpl dbService = new DBServiceImpl(ConnectionHelper.getConnection());
        UserDataSet user = dbService.read(3);

        assertNull(user);
    }


}