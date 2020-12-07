package world.ucode.services;

import world.ucode.dao.BidDao;
import world.ucode.dao.LotDao;
import world.ucode.models.Bid;
import world.ucode.models.Lot;

import java.util.List;

public class BidService {
    private final BidDao bidDao = new BidDao();

    public BidService() {
    }

    public Bid findBid(int id) {
        return bidDao.findById(id);
    }

    public void saveBid(Bid bid) {
        //set previous bid to false
        try {
            Bid prevBid = this.findLast(bid.getLot().getId());
            if (prevBid != null) {
                prevBid.setActive(false);
                this.updateBid(prevBid);
            }
        }
        catch (Exception ignored){}
        finally {
            bidDao.save(bid);
        }
    }

    public void deleteBid(Bid bid) {
        bidDao.delete(bid);
    }

    public void updateBid(Bid bid) {
        bidDao.update(bid);
    }

    public List<Bid> findAllBids() {
        return bidDao.findAll();
    }

    public List<Bid> findAllBidsByUser(String login) {
        return bidDao.findAllByUser(login);
    }

    public Bid findLast(int lotId) {
        return bidDao.findLast(lotId);
    }
}
