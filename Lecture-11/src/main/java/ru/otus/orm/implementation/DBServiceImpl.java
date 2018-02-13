package ru.otus.orm.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.otus.cache.CacheEngine;
import ru.otus.cache.CacheEngineImpl;
import ru.otus.cache.MyElement;
import ru.otus.orm.dao.AddressDAO;
import ru.otus.orm.dao.PhoneDAO;
import ru.otus.orm.dao.UserDAO;
import ru.otus.orm.dataset.AddressDataSet;
import ru.otus.orm.dataset.DataSet;
import ru.otus.orm.dataset.PhoneDataSet;
import ru.otus.orm.dataset.UserDataSet;
import ru.otus.orm.interfaces.IDBService;

public class DBServiceImpl implements IDBService {
    private final SessionFactory sessionFactory;
    private CacheEngine<Long, DataSet> cacheEngine;

    private final int maxElements = 5;
    private final long lifeTimeMs = 0;
    private final long idleTimeMs = 0;
    private final boolean isEternal = true;

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
        cacheEngine = new CacheEngineImpl<>(maxElements, lifeTimeMs, idleTimeMs, isEternal);
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Override
    public void save(UserDataSet dataSet) {
        try (Session session = sessionFactory.openSession()) {
            UserDAO dao = new UserDAO(session);
            dao.save(dataSet);
            cacheEngine.put(dataSet);
        }
    }
    @Override
    public UserDataSet read(long id) {
        try (Session session = sessionFactory.openSession()) {
            UserDAO dao = new UserDAO(session);
            UserDataSet user = cacheEngine.get(id);

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
            cacheEngine.put(dataSet);
        }
    }
    @Override
    public AddressDataSet readAddress(long id) {
        try (Session session = sessionFactory.openSession()) {
            AddressDAO dao = new AddressDAO(session);
            AddressDataSet address = cacheEngine.get(id);

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
            cacheEngine.put(dataSet);
        }
    }
    @Override
    public PhoneDataSet readPhone(long id) {
        try (Session session = sessionFactory.openSession()) {
            PhoneDAO dao = new PhoneDAO(session);
            PhoneDataSet phone = cacheEngine.get(id);

            if (phone == null) {
                return dao.read(id);
            } else {
                return phone;
            }
        }
    }
}
