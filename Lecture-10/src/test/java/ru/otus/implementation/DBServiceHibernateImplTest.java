package ru.otus.implementation;

import org.junit.Test;
import ru.otus.dataset.AddressDataSet;
import ru.otus.dataset.PhoneDataSet;
import ru.otus.dataset.UserDataSet;
import ru.otus.interfaces.IDBService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DBServiceHibernateImplTest {
    /**
     * + 2. Не меняя интерфейс DBService сделать DBServiceHibernateImpl на Hibernate.
     * + 3. Добавить в UsersDataSet поля:
             адресс (OneToOne)
             class AddressDataSet{
                private String street;
             }
             и телефон* (OneToMany)
             class PhoneDataSet{
                private String number;
             }
             Добавить соответствущие датасеты и DAO.
     * */

    //@Test
    public void shouldSave() throws Exception {
        IDBService dbService = new DBServiceHibernateImpl();

        AddressDataSet address = new AddressDataSet();
        address.setStreet("some address of user 3");

        List<PhoneDataSet> phones = new ArrayList<>();
        PhoneDataSet phone = new PhoneDataSet();
        phone.setPhone("12345");
        phones.add(phone);

        phone = new PhoneDataSet();
        phone.setPhone("54321");
        phones.add(phone);

        UserDataSet user = new UserDataSet();
        user.setAge(25);
        user.setName("user3");
        user.setAddress(address);
        user.setPhone(phones);

        dbService.save(user);
    }

    //@Test
    public void serviceAddressShouldSave() throws Exception {
        IDBService dbService = new DBServiceHibernateImpl();
        AddressDataSet addressDataSet = new AddressDataSet();
        addressDataSet.setStreet("some address for user");

        dbService.saveAddress(addressDataSet);
    }

    //@Test
    public void servicePhoneShouldSave() throws Exception {
        IDBService dbService = new DBServiceHibernateImpl();

        UserDataSet user = dbService.read(1);
        user.getId();

        PhoneDataSet phone = new PhoneDataSet();
        phone.setUserId(user);
        phone.setPhone("55134");
        dbService.savePhone(phone);

        phone = new PhoneDataSet();
        phone.setUserId(user);
        phone.setPhone("54448");
        dbService.savePhone(phone);
    }

    @Test
    public void shouldRead() throws Exception {
        IDBService dbService = new DBServiceHibernateImpl();
        UserDataSet user = dbService.read(1);

        assertEquals(user.getName(), "user1");
        assertEquals(user.getAge(), 15);
    }

    @Test
    public void shouldReadUserAddress() throws Exception {
        IDBService dbService = new DBServiceHibernateImpl();
        AddressDataSet address = dbService.readAddress(1);

        assertEquals(address.getId(), 1);
        assertEquals(address.getStreet(), "some address of user 2");
    }

    @Test
    public void shouldReadUserPhone() throws Exception {
        IDBService dbService = new DBServiceHibernateImpl();
        PhoneDataSet phone = dbService.readPhone(1);

        assertEquals(phone.getId(), 1);
        assertEquals(phone.getUserId().getId(), 1);
        assertEquals(phone.getPhone(), "55134");
    }
}