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
        Bid prevBid = this.findLast(bid.getId());
        if (prevBid != null) {
            prevBid.setActive(false);
            this.updateBid(prevBid);
            System.out.println("hello bid");
        }
        bidDao.save(bid);
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
