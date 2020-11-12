package world.ucode.controller;

import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;

public class Usr {
    public String type;
    public String username;
    public String password;
    public String confirmpassword;
    public String email;
    public Usr(String type, String username, String password, String confirmpassword, String email) {
        this.type = type;
        this.username = username;
        this.password = password;
        this.confirmpassword = confirmpassword;
        this.email = email;
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
}
