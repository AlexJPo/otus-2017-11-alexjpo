package ru.otus.interfaces;

import ru.otus.dataset.AddressDataSet;
import ru.otus.dataset.PhoneDataSet;
import ru.otus.dataset.UserDataSet;

public interface IDBService {
    void save(UserDataSet dataSet);

    UserDataSet read(long id);

    void savePhone(PhoneDataSet dataSet);

    PhoneDataSet readPhone(long id);

    void saveAddress(AddressDataSet dataSet);

    AddressDataSet readAddress(long id);
}
