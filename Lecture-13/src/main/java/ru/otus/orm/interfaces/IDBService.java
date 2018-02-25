package ru.otus.orm.interfaces;

import ru.otus.orm.dataset.AddressDataSet;
import ru.otus.orm.dataset.PhoneDataSet;
import ru.otus.orm.dataset.UserDataSet;

public interface IDBService {
    void save(UserDataSet dataSet);

    UserDataSet read(long id);

    void savePhone(PhoneDataSet dataSet);

    PhoneDataSet readPhone(long id);

    void saveAddress(AddressDataSet dataSet);

    AddressDataSet readAddress(long id);
}
