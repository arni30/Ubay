package world.ucode.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import world.ucode.models.Lot;
import world.ucode.models.User;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration config = new Configuration().configure();

//                config.addAnnotatedClass(User.class);
//                config.addAnnotatedClass(Lot.class);
//                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(config.getProperties());
//                sessionFactory = config.buildSessionFactory(builder.build());

                config.configure(); // замість getProperties
                sessionFactory = config.buildSessionFactory();

                System.out.println(" --- Hibernate Java Config service Registry created");

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
//                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
