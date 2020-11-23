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
    private double startPrice;
    @Column(name = "bidStep")
    private double bidStep;
    @Column(name = "duration")
    private int duration;
    @Column(name = "description")
    private String description;
    @Column(name = "category")
    private String category;
    //    private startTime;
//    private boolean finished;

//    private BufferedImage image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sellerId", referencedColumnName = "userId")
    private User seller;

    public Lot() {}
    public Lot(String title, int startPrice) {
        this.title = title;
        this .startPrice = startPrice;
    }

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

    //    public void setFinished(boolean finished) { this.finished = finished; }
    //    public boolean getFinished() { return finished; }

    public void setSeller(User seller) { this.seller = seller; }
    public User getSeller() { return seller; }

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
