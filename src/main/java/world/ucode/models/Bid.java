package world.ucode.models;

import javax.persistence.*;

@Entity
@Table(name = "bids")
public class Bid {
    @Id
    @Column(name="bidId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "bidderId")
    private int bidderId;
    @Column(name = "auctionId")
    private int auctionId;
    @Column(name = "price")
    private int price;

    public int getId() {
        return id;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }
    public int getAuctionId() {
        return auctionId;
    }

    public void setBidderId(int bidderId) {
        this.bidderId = bidderId;
    }
    public int getBidderId() {
        return bidderId;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public int getPrice() {
        return price;
    }
}
