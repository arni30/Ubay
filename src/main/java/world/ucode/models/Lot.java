package world.ucode.models;

import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.security.PublicKey;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lots")
public class Lot {
    @Id
    @Column(name="lotId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "startPrice")
    private double startPrice;
    @Column(name = "bidStep")
    private double bidStep;
    @Column(name = "duration")
    private int duration;
    @Column(name = "description")
    private String description;
    @Column(name = "category")
    private String category;
    @Column(name = "startTime")
    private Timestamp startTime;
    @Column(name = "finishTime")
    private Timestamp finishTime;
    @Column(name = "active")
    private boolean active;

//    private BufferedImage image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sellerId", referencedColumnName = "userId", insertable = false, updatable = false, nullable = false)
    private User seller;

//    @OneToOne(mappedBy = "auctionId",cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.LAZY)
//    private Bid lastBid;

    public int getId() { return id; }

    public void setTitle(String title) { this.title = title; }
    public String getTitle() { return title; }

    public void setStartPrice(double startPrice) { this.startPrice = startPrice; }
    public double getStartPrice() { return startPrice; }

    public void setBidStep(double bidStep) { this.bidStep = bidStep; }
    public double getBidStep() { return bidStep; }

    public void setDuration(int duration) { this.duration = duration; }
    public int getDuration() { return duration; }

    public void setDescription(String description) { this.description = description; }
    public String getDescription() { return description; }

    public void setCategory(String category) { this.category = category; }
    public String getCategory() { return category; }

    public void setSeller(User seller) { this.seller = seller; }
    public User getSeller() { return seller; }

    public void setStartTime(Timestamp startTime) { this.startTime = startTime; }
    public Timestamp getStartTime() { return startTime; }

    public void setFinishTime(Timestamp finishTime) { this.finishTime = finishTime; }
    public Timestamp getFinishTime() { return finishTime; }

    public void setActive(boolean active) { this.active = active; }
    public boolean getActive() { return active; }

//    public void setLastBid(Bid lastBid) { this.lastBid = lastBid; }
//    public Bid getLastBid() { return lastBid; }

    @Override
    public String toString() {
        String res = "models.Lot{ " +
                "id=" + id +
                ", title='" + title + '\'' +
                ", startPrice=" + startPrice +
                ", duration=" + duration +
                ", description='" + description + "'}";
        return res;
    }

}
