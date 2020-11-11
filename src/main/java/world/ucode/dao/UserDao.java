package world.ucode.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import world.ucode.models.Lot;
import world.ucode.models.User;
import world.ucode.utils.HibernateSessionFactoryUtil;

import java.util.List;

public class UserDao {

    public User findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(User.class, id);
    }

    public void save(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    public void update(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(user);
        tx1.commit();
        session.close();
    }

    public void delete(User user) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(user);
        tx1.commit();
        session.close();
    }

    public Lot findLotById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Lot.class, id);
    }

    public List<User> findAll() {
        List<User> users = (List<User>)HibernateSessionFactoryUtil.getSessionFactory()
                .openSession().createQuery("From User").list();
        return users;
    }
}
