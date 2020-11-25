package world.ucode.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import world.ucode.models.Lot;
import world.ucode.models.User;
import world.ucode.utils.HibernateSessionFactoryUtil;

import java.util.List;

public class LotDao {
    public Lot findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Lot.class, id);
    }

    public Lot findByTitle(String title) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query =  session.createQuery("SELECT lot FROM Lot lot WHERE lot.title = :title").setParameter("title", title);
        Lot lot = (Lot) query.uniqueResult();
        session.close();
        return lot;
    }

    public void save(Lot lot) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(lot);
        tx1.commit();
        session.close();
    }

    public void update(Lot lot) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(lot);
        tx1.commit();
        session.close();
    }

    public void delete(Lot lot) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(lot);
        tx1.commit();
        session.close();
    }

    public List<Lot> findAll() {
        List<Lot> lots = (List<Lot>)HibernateSessionFactoryUtil.getSessionFactory()
                .openSession().createQuery("From Lot").list();
        return lots;
    }

    public List<Lot> findAllByUser(String login) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("SELECT lot FROM Lot lot WHERE lot.seller.login = :login")
                .setParameter("login", login);
        List<Lot> lots = (List<Lot>) query.getResultList();
        session.close();
        return lots;
    }
}
