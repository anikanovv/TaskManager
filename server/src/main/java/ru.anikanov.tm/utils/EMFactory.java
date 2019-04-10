package ru.anikanov.tm.utils;

import org.apache.deltaspike.jpa.api.transaction.TransactionScoped;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.jetbrains.annotations.NotNull;
import ru.anikanov.tm.entity.Project;
import ru.anikanov.tm.entity.Session;
import ru.anikanov.tm.entity.Task;
import ru.anikanov.tm.entity.User;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class EMFactory {
    private EntityManagerFactory factory() {
        final Map<String, String> settings = new HashMap<>();
        @NotNull final Properties property = PropertiesUtil.getProperties();
        settings.put(Environment.DRIVER, property.getProperty("db.driver"));
        settings.put(Environment.URL, property.getProperty("db.host"));
        settings.put(Environment.USER, property.getProperty("db.login"));
        settings.put(Environment.PASS, property.getProperty("db.password"));
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5InnoDBDialect");
        settings.put(Environment.HBM2DDL_AUTO, "update");
        settings.put(Environment.SHOW_SQL, "true");
        final StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
        registryBuilder.applySettings(settings);
        final StandardServiceRegistry registry = registryBuilder.build();
        final MetadataSources sources = new MetadataSources(registry);
        sources.addAnnotatedClass(User.class);
        sources.addAnnotatedClass(Session.class);
        sources.addAnnotatedClass(Task.class);
        sources.addAnnotatedClass(Project.class);
        final Metadata metadata = sources.getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
    }

    @Produces
    @TransactionScoped
    public EntityManager getEntityManager() {
        return factory().createEntityManager();
    }

}
