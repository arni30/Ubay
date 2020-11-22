package world.ucode.models;

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

//    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Lot> lots;

    public User() {}
//    public User(String login, String password) {
//        System.out.println("CONSTRUCTOR");
//        this.login = login;
//        this.password = password;
//        lots = new ArrayList<>();
//    }
//
//    public void addLot(Lot lot) {
//        lot.setSeller(this);
//        lots.add(lot);
//    }
//    public void removeLot(Lot lot) {
//        lots.remove(lot);
//    }
//    public List<Lot> getLots() {
//        return lots;
//    }
//    public void setLots(ArrayList<Lot> lots) {
//        this.lots = lots;
//    }

    public int getId() {
        return id;
    }

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

    @Override
    public String toString() {
        String res = "models.User{ " +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password=" + password +
                ", role=";
        if (userRole.equals("seller")) {
            res += "seller";
        } else {
            res += "bitter";
        }
        res += "(" + userRole + ") }";
        return res;
    }
}
