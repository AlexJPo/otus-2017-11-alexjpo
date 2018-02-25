package ru.otus;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.otus.cache.CacheEngineImpl;
import ru.otus.cache.ICacheEngine;
import ru.otus.orm.implementations.DBServiceImpl;

@Configuration
@ComponentScan(basePackages = "ru.otus")
public class AppConfiguration {
    @Bean(name = "dbServiceBean")
    public DBServiceImpl dbServiceBean() {
        return new DBServiceImpl();
    }

    @Bean(name = "cacheEngine")
    public ICacheEngine cacheEngine() {
        return new CacheEngineImpl(5, 0, 0, true);
    }
}
