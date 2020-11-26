package world.ucode.models;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table (name = "users")
public class User {
    private String type;

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Id
    @Column(name="userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "userRole")
    private String userRole;
    @Column(name = "email")
    private String email;
    @Column(name = "balance")
    private double balance;
    @Column(name = "token")
    private String token;
    @Column(name = "verification")
    private String verification;
    @Column(name = "avarageRate")
    private double avarageRate;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lot> lots = new ArrayList<Lot>();

    @OneToMany(mappedBy = "bidder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bid> bids = new ArrayList<Bid>();

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        this.password = bCryptPasswordEncoder.encode(password);
//        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        this.password = password;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getUserRole() { return userRole; }
    public void setUserRole(String userRole) { this.userRole = userRole; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public String getVerification() {
        return verification;
    }
    public void setVerification(String verification) {
        this.verification = verification;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public void setAvarageRate(double avarageRate) { this.avarageRate = avarageRate; }
    public double getAvarageRate() { return avarageRate; }

    public void addLot(Lot lot) {
        lot.setSeller(this);
        lots.add(lot);
    }
    public void removeLot(Lot lot) { lots.remove(lot); }

    public List<Lot> getLots() { return lots; }
    public void setLots(ArrayList<Lot> lots) { this.lots = lots; }

    public void addBid(Bid bid) {
        bid.setBidder(this);
        bids.add(bid);
    }
    public void removeBid(Bid bid) { bids.remove(bid); }

    public List<Bid> getBids() { return bids; }
    public void setBids(ArrayList<Bid> bids) { this.bids = bids; }
    //    @Override
//    public String toString() {
//        String res = "models.User{ " +
//                "id=" + id +
//                ", login='" + login + '\'' +
//                ", password=" + password +
//                ", role=";
//        if (userRole.equals("seller")) {
//            res += "seller";
//        } else {
//            res += "bitter";
//        }
//        res += "(" + userRole + ") }";
//        return res;
//    }
}
