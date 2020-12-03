package world.ucode.models;

import javax.persistence.*;

@Entity
@Table(name = "bids")
public class Bid {
    @Id
    @Column(name="bidId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "price")
    private int price;
    @Column(name = "active")
    private boolean active;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "auctionId", referencedColumnName = "lotId")
    private Lot lot;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bidderId", referencedColumnName = "userId")
    private User bidder;

    public int getId() {
        return id;
    }

    public void setLot(Lot lot) {
        this.lot = lot;
//        lot.setLastBid(this);
    }
    public Lot getLot() {
        return lot;
    }

    public void setBidder(User bidder) {
        this.bidder = bidder;
    }
    public User getBidder() {
        return bidder;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public int getPrice() {
        return price;
    }

    public void setActive(boolean active) { this.active = active; }
    public boolean getActive() { return active; }
}
