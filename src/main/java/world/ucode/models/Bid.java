package world.ucode.models;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;

@Entity
@Table(name = "bids")
public class Bid {
    @Id
    @Column(name="bidId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "price")
    private double price;
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

    public void setPrice(double price) {
        this.price = price;
    }
    public double getPrice() {
        return price;
    }

    public void setActive(boolean active) { this.active = active; }
    public boolean getActive() { return active; }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(lot)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Bid other = (Bid) obj;
        if (id == 0) {
            return other.id == 0;
        } else return lot.equals(other.lot);
    }
}
