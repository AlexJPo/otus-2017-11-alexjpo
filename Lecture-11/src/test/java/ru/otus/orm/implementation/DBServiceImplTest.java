package ru.otus.orm.implementation;

import org.junit.Before;
import org.junit.Test;
import ru.otus.orm.dataset.AddressDataSet;
import ru.otus.orm.dataset.PhoneDataSet;
import ru.otus.orm.dataset.UserDataSet;
import ru.otus.orm.interfaces.IDBService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DBServiceImplTest {
    private IDBService dbService;

    @Before
    public void initDBService() {
        dbService = new DBServiceImpl();
    }

    @Test
    public void shouldSave() throws Exception {
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

        UserDataSet userRead = dbService.read(1);
        assertEquals(user.getName(), userRead.getName());
        assertEquals(user.getAge(), userRead.getAge());
    }

    @Test
    public void serviceAddressShouldSave() throws Exception {
        AddressDataSet addressDataSet = new AddressDataSet();
        addressDataSet.setStreet("some address for user");
        dbService.saveAddress(addressDataSet);

        AddressDataSet address = dbService.readAddress(1);
        assertEquals(addressDataSet.getId(), address.getId());
        assertEquals(addressDataSet.getStreet(), address.getStreet());

    }

    @Test
    public void servicePhoneShouldSave() throws Exception {
        UserDataSet user = new UserDataSet();
        user.setAge(25);
        user.setName("user3");
        dbService.save(user);

        UserDataSet savedUser = dbService.read(1);

        PhoneDataSet phoneDataSet = new PhoneDataSet();
        phoneDataSet.setUserId(savedUser);
        phoneDataSet.setPhone("55134");
        dbService.savePhone(phoneDataSet);

        PhoneDataSet phone = dbService.readPhone(1);
        assertEquals(phoneDataSet.getId(), phone.getId(), 1);
        assertEquals(phoneDataSet.getUserId().getId(), phone.getUserId().getId());
        assertEquals(phoneDataSet.getPhone(), phone.getPhone());
    }
}