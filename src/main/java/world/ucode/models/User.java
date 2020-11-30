package world.ucode.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table (name = "users")
public class User implements UserDetails {
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
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lot> lots;

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

    public void setPassword(String password) {
//        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
//        this.password = bCryptPasswordEncoder.encode(password);
//        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        this.password = password;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

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
