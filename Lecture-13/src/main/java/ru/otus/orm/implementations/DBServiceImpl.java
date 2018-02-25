package ru.otus.orm.implementations;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.otus.cache.CacheEngineImpl;
import ru.otus.cache.ICacheEngine;
import ru.otus.orm.dao.AddressDAO;
import ru.otus.orm.dao.PhoneDAO;
import ru.otus.orm.dao.UserDAO;
import ru.otus.orm.dataset.AddressDataSet;
import ru.otus.orm.dataset.DataSet;
import ru.otus.orm.dataset.PhoneDataSet;
import ru.otus.orm.dataset.UserDataSet;
import ru.otus.orm.interfaces.IDBService;

import java.util.Map;

@Service
public class DBServiceImpl implements IDBService {
    private final SessionFactory sessionFactory;

    @Autowired
    private ICacheEngine<Long, DataSet> cacheEngine;

    public DBServiceImpl() {
        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(UserDataSet.class);
        configuration.addAnnotatedClass(AddressDataSet.class);
        configuration.addAnnotatedClass(PhoneDataSet.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL94Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://127.0.0.1:5432/otus");
        configuration.setProperty("hibernate.connection.username", "postgres");
        configuration.setProperty("hibernate.connection.password", "");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        configuration.setProperty("hibernate.connection.useSSL", "false");
        configuration.setProperty("hibernate.enable_lazy_load_no_trans", "true");

        sessionFactory = createSessionFactory(configuration);
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public void prepareData() {
        AddressDataSet addressDataSet = new AddressDataSet();
        addressDataSet.setStreet("some address for user");
        saveAddress(addressDataSet);

        UserDataSet userDataSet = new UserDataSet();
        userDataSet.setAge(25);
        userDataSet.setName("user1");
        save(userDataSet);

        PhoneDataSet phoneDataSet = new PhoneDataSet();
        phoneDataSet.setUserId(userDataSet);
        phoneDataSet.setPhone("55134");
        savePhone(phoneDataSet);

        readData();
    }

    public void readData() {
        readAddress(1);
        read(1);
        readPhone(1);

        readAddress(2);
        readPhone(3);
    }

    @Override
    public void save(UserDataSet dataSet) {
        try (Session session = sessionFactory.openSession()) {
            UserDAO dao = new UserDAO(session);
            dao.save(dataSet);
            cacheEngine.put(dataSet.getId(), dataSet);
        }
    }
    @Override
    public UserDataSet read(long id) {
        try (Session session = sessionFactory.openSession()) {
            UserDAO dao = new UserDAO(session);
            UserDataSet user = cacheEngine.get(id, UserDataSet.class);

            if (user == null) {
                return dao.read(id);
            } else {
                return user;
            }
        }
    }

    @Override
    public void saveAddress(AddressDataSet dataSet) {
        try (Session session = sessionFactory.openSession()) {
            AddressDAO dao = new AddressDAO(session);
            dao.save(dataSet);
            cacheEngine.put(dataSet.getId(), dataSet);
        }
    }
    @Override
    public AddressDataSet readAddress(long id) {
        try (Session session = sessionFactory.openSession()) {
            AddressDAO dao = new AddressDAO(session);
            AddressDataSet address = cacheEngine.get(id, AddressDataSet.class);

            if (address == null) {
                return dao.read(id);
            } else {
                return address;
            }
        }
    }

    @Override
    public void savePhone(PhoneDataSet dataSet) {
        try (Session session = sessionFactory.openSession()) {
            PhoneDAO dao = new PhoneDAO(session);
            dao.save(dataSet);
            cacheEngine.put(dataSet.getId(), dataSet);
        }
    }
    @Override
    public PhoneDataSet readPhone(long id) {
        try (Session session = sessionFactory.openSession()) {
            PhoneDAO dao = new PhoneDAO(session);
            PhoneDataSet phone = cacheEngine.get(id, PhoneDataSet.class);

            if (phone == null) {
                return dao.read(id);
            } else {
                return phone;
            }
        }
    }

    public Map<String, Object> getCacheStatus() {
        return cacheEngine.getStatus();
    }
}
