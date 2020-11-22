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
    //private double rate;
}
