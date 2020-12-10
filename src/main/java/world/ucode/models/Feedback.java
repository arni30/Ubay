package world.ucode.models;

import javax.persistence.*;

@Entity
@Table(name = "feedbacks")
public class Feedback {
    @Id
    @Column(name="fbId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "description")
    private String description;
    @Column(name = "rate")
    private double rate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "auctionId", referencedColumnName = "lotId")
    private Lot lot;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bidderId", referencedColumnName = "userId")
    private User bidder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sellerId", referencedColumnName = "userId")
    private User seller;

    public int getId() { return id; }

    public void setBidder(User bidder) { this.bidder = bidder; }
    public User getBidder() { return bidder; }

    public void setLot(Lot lot) { this.lot = lot; }
    public Lot getLot() { return lot; }

    public void setSeller(User seller) { this.seller = seller; }
    public User getSeller() { return seller; }

    public void setDescription(String description) { this.description = description; }
    public String getDescription() { return description; }

    public void setRate(double rate) { this.rate = rate; }
    public double getRate() { return rate; }
}
