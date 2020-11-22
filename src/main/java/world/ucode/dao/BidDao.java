package world.ucode.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import world.ucode.models.Bid;
import world.ucode.models.Lot;
import world.ucode.utils.HibernateSessionFactoryUtil;

import java.util.List;

public class BidDao {
    public Bid findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Bid.class, id);
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
        List<Bid> bids = (List<Bid>)HibernateSessionFactoryUtil.getSessionFactory()
                .openSession().createQuery("From Bid").list();
        return bids;
    }
}
