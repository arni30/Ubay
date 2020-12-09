package world.ucode.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import world.ucode.models.Bid;
import world.ucode.utils.HibernateSessionFactoryUtil;

import java.util.List;

public class BidDao {
    public Bid findById(int id) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Bid bid = session.get(Bid.class, id);
        session.close();
        return bid;
    }

    public void save(Bid bid) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(bid);
        tx1.commit();
        session.close();
    }

    public void update(Bid bid) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(bid);
        tx1.commit();
        session.close();
    }

    public void delete(Bid bid) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(bid);
        tx1.commit();
        session.close();
    }

    public List<Bid> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Bid> bids = (List<Bid>)session.createQuery("From Bid").list();
        session.close();
        return bids;
    }

    public Bid findLast(int lotId) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Bid> bids = (List<Bid>)session.createQuery("SELECT bid FROM Bid bid WHERE bid.lot.id = :lotId").setParameter("lotId", lotId).list();
        Bid bid = bids.size() == 0 ? null : bids.get(bids.size() - 1);
        session.close();
        return bid;
    }
}
