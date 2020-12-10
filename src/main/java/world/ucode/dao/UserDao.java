package world.ucode.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import world.ucode.models.Bid;
import world.ucode.models.Lot;
import world.ucode.models.User;
import world.ucode.utils.HibernateSessionFactoryUtil;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.*;

public class UserDao {

    public User findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }

    public User findByLogin(String login) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query =  session.createQuery("SELECT user FROM User user WHERE user.login = :login").setParameter("login", login);
        User user = (User) query.uniqueResult();
        session.close();
        return user;
    }
    public User findByToken(String token) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query =  session.createQuery("SELECT user FROM User user WHERE user.token = :token").setParameter("token", token);
        User user = (User) query.uniqueResult();
        session.close();
        return user;
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
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Lot lot = session.get(Lot.class, id);
        session.close();
        return lot;
    }

    public Set<Bid> findBidsByBidder(String login) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Bid> bids = (List<Bid>)session.createQuery
                ("SELECT bid FROM Bid bid WHERE bid.bidder.login = :login")
                .setParameter("login", login).list();
        session.close();
        Collections.reverse(bids);
        return new HashSet<Bid>(bids);
    }

    public List<User> findAll() {
        List<User> users = (List<User>)HibernateSessionFactoryUtil.getSessionFactory()
                .openSession().createQuery("From User").list();
        return users;
    }
}
