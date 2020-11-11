package world.ucode.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "login")
    private String name;

    private String password;
    private int userRole;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lot> lots;

    public User() {}
    public User(String name, String password, int role) {
        this.name = name;
        this.password = password;
        this.userRole = role;
        lots = new ArrayList<>();
    }

    public void addLot(Lot lot) {
        lot.setSeller(this);
        lots.add(lot);
    }
    public void removeLot(Lot lot) {
        lots.remove(lot);
    }
    public List<Lot> getLots() {
        return lots;
    }
    public void setLots(ArrayList<Lot> lots) {
        this.lots = lots;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return userRole;
    }
    public void setRole(int role) {
        this.userRole = role;
    }

    @Override
    public String toString() {
        String res = "models.User{ " +
                "id=" + id +
                ", login='" + name + '\'' +
                ", password=" + password +
                ", role=";
        if (userRole == 0) {
            res += "seller";
        } else {
            res += "bitter";
        }
        res += "(" + userRole + ") }";
        return res;
    }
}
