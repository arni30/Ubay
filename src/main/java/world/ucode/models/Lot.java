package world.ucode.models;

import javax.persistence.*;
import java.awt.image.BufferedImage;
import java.security.PublicKey;

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
    private int startPrice;
//    private int bidPrice;
    @Column(name = "duration")
    private int duration;
    @Column(name = "description")
    private String description;
//    private boolean finished;

//    private BufferedImage image;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "sellerId", referencedColumnName = "userId")
//    private User seller;

    public Lot() {}
    public Lot(String title, int startPrice) {
        this.title = title;
        this .startPrice = startPrice;
    }

    public int getId() { return id; }

    public void setTitle(String title) { this.title = title; }
    public String getTitle() { return title; }

    public void setStartPrice(int startPrice) { this.startPrice = startPrice; }
    public int getStartPrice() { return startPrice; }

//    public void setBidPrice(int bidPrice) { this.bidPrice = bidPrice; }
//    public int getBidPrice() { return bidPrice; }

    public void setDuration(int duration) { this.duration = duration; }
    public int getDuration() { return duration; }

    public void setDescription(String description) { this.description = description; }
    public String getDescription() { return description; }

//    public void setFinished(boolean finished) { this.finished = finished; }
//    public boolean getFinished() { return finished; }

//    public void setSeller(User seller) { this.seller = seller; }
//    public User getSeller() { return seller; }

    @Override
    public String toString() {
        return title + ", " + startPrice + "$";
    }

}
