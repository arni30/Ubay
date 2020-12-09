package world.ucode.services;

import world.ucode.dao.FeedbackDao;
import world.ucode.models.Feedback;

import java.util.List;

public class FeedbackService {
    private final FeedbackDao feedbackDao = new FeedbackDao();

    public Feedback findFeedback(int id) {
        return feedbackDao.findById(id);
    }

    public Feedback findFeedbackByLot(int lotId) {
        return feedbackDao.findByLot(lotId);
    }

    public void saveFeedback(Feedback feedback) { feedbackDao.save(feedback); }

    public void deleteFeedback(Feedback feedback) {
        feedbackDao.delete(feedback);
    }

    public void updateFeedback(Feedback feedback) {
        feedbackDao.update(feedback);
    }

    public List<Feedback> findAllFeedbacks() {
        return feedbackDao.findAll();
    }

    public List<Feedback> findAllByUser(String login) {
        return feedbackDao.findAllByUser(login);
    }
}
