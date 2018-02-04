package ru.otus.implementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.otus.dao.AddressHibernateDAO;
import ru.otus.dao.PhoneDAO;
import ru.otus.dao.UserHibernateDAO;
import ru.otus.dataset.AddressDataSet;
import ru.otus.dataset.PhoneDataSet;
import ru.otus.dataset.UserDataSet;
import ru.otus.interfaces.IDBService;

public class DBServiceHibernateImpl implements IDBService {
    private final SessionFactory sessionFactory;

    public DBServiceHibernateImpl() {
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
        configuration.setProperty("hibernate.hbm2ddl.auto", "none");
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

    @Override
    public void save(UserDataSet dataSet) {
        try (Session session = sessionFactory.openSession()) {
            UserHibernateDAO dao = new UserHibernateDAO(session);
            dao.save(dataSet);
        }
    }
    @Override
    public UserDataSet read(long id) {
        try (Session session = sessionFactory.openSession()) {
            UserHibernateDAO dao = new UserHibernateDAO(session);
            return dao.read(id);
        }
    }

    @Override
    public void saveAddress(AddressDataSet dataSet) {
        try (Session session = sessionFactory.openSession()) {
            AddressHibernateDAO dao = new AddressHibernateDAO(session);
            dao.save(dataSet);
        }
    }
    @Override
    public AddressDataSet readAddress(long id) {
        try (Session session = sessionFactory.openSession()) {
            AddressHibernateDAO dao = new AddressHibernateDAO(session);
            return dao.read(id);
        }
    }

    @Override
    public void savePhone(PhoneDataSet dataSet) {
        try (Session session = sessionFactory.openSession()) {
            PhoneDAO dao = new PhoneDAO(session);
            dao.save(dataSet);
        }
    }
    @Override
    public PhoneDataSet readPhone(long id) {
        try (Session session = sessionFactory.openSession()) {
            PhoneDAO dao = new PhoneDAO(session);
            return dao.read(id);
        }
    }
}
