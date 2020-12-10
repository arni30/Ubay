package world.ucode.models;

import org.apache.commons.lang3.builder.EqualsBuilder;

import javax.persistence.*;
import java.sql.Timestamp;

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
    @Lob()
    private byte[] image;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sellerId", referencedColumnName = "userId")
    private User seller;

    public Lot() {}

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

    public byte[] getImage() {
        return image;
    }
    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Lot) {
            final Lot lot = (Lot) obj;

            return new EqualsBuilder()
                    .append(id, lot.id)
                    .isEquals();
        } else {
            return false;
        }
    }
}
