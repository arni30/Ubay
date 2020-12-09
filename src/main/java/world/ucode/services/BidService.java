package world.ucode.services;

import world.ucode.dao.BidDao;
import world.ucode.models.Bid;

import java.util.List;

public class BidService {
    private final BidDao bidDao = new BidDao();

    public Bid findBid(int id) {
        return bidDao.findById(id);
    }

    public void saveBid(Bid bid) {
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

    public Bid findLast(int lotId) {
        return bidDao.findLast(lotId);
    }
}
