package world.ucode.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import world.ucode.models.Bid;
import world.ucode.models.Feedback;
import world.ucode.utils.HibernateSessionFactoryUtil;

import java.util.List;

public class FeedbackDao {
    public Feedback findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Feedback.class, id);
    }

    public void save(Feedback feedback) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(feedback);
        tx1.commit();
        session.close();
    }

    public void update(Feedback feedback) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(feedback);
        tx1.commit();
        session.close();
    }

    public void delete(Feedback feedback) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(feedback);
        tx1.commit();
        session.close();
    }

    public List<Feedback> findAll() {
        List<Feedback> feedbacks = (List<Feedback>)HibernateSessionFactoryUtil.getSessionFactory()
                .openSession().createQuery("From Feedback").list();
        return feedbacks;
    }
}
