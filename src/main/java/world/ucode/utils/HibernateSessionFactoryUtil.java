package world.ucode.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration config = new Configuration().configure();
                config.configure();
                sessionFactory = config.buildSessionFactory();
            } catch (Exception e) {
                System.out.println("Exception! Start MySql server!");
            }
        }
        return sessionFactory;
    }
}
