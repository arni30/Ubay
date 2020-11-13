package world.ucode.controller;

import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;

public class Usr {
    private String type;
    private String username;
    private String password;
    private String confirmpassword;
    private String email;
    private String balance;
    private String role;

    public void setRole(String role) {
        this.role = role;
    }
    public void setBalance(String balance) {
        this.balance = balance;
    }
    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getConfirmpassword() {
        return this.confirmpassword;
    }

    public String getRole() {
        return this.role;
    }

    public String getBalance() {
        return this.balance;
    }

    public String getType() {
        return type;
    }
}
