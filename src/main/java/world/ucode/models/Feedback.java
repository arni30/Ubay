package world.ucode.models;

import javax.persistence.*;

@Entity
@Table(name = "feedbacks")
public class Feedback {
    @Id
    @Column(name="fbId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "userId")
    private int userId;
    @Column(name = "auctionId")
    private int auctionId;
    @Column(name = "description")
    private String description;
    @Column(name = "rate")
    private double rate;

    public int getId() { return id; }

    public void setUserId(int userId) { this.userId = userId; }
    public int getUserId() { return userId; }

    public void setAuctionId(int auctionId) { this.auctionId = auctionId; }
    public int getAuctionId() { return auctionId; }

    public void setDescription(String description) { this.description = description; }
    public String getDescription() { return description; }

    public void setRate(double rate) { this.rate = rate; }
    public double getRate() { return rate; }
}
