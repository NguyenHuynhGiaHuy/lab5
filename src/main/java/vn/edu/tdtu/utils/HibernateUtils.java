package vn.edu.tdtu.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import vn.edu.tdtu.model.User;

public class HibernateUtils {

    private static final SessionFactory sessionFactory;

    private HibernateUtils() {
        super();
    }

    static {
        Configuration configuration = new Configuration();
        //configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(User.class);
        sessionFactory = buildSessionFactory();
    }

    private static SessionFactory buildSessionFactory() {
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder() //
                .configure() // Load hibernate.cfg.xml from resource folder by default
                .build();
        Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void close() {
        getSessionFactory().close();
    }
}