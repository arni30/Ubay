package world.ucode.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import world.ucode.models.Role;
import world.ucode.models.User;
import world.ucode.security.Token;
import world.ucode.services.UserService;
import world.ucode.utils.SendMail;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;

@Controller
public class RegistrationController {
    @Autowired
    UserService userService;
    @Autowired
    SendMail sendMail;
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public RedirectView signup_post(User user, HttpServletResponse response) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        RedirectView rv = null;
        String json = "";
        if (userService.findUser(user.getLogin()) == null) {
            if (user.getUserRole().equals("seller"))
                user.setRoles(Collections.singleton(Role.SELLER));
            else
                user.setRoles(Collections.singleton(Role.BIDDER));
            Token token = new Token();
            user.setAvarageRate(0);
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
            user.setToken(token.getJWTToken(user.getLogin()));
            sendMail.sendMailConfirmation(user);
            response.addCookie(new Cookie("login", user.getLogin()));
            userService.saveUser(user);
            json = mapper.writeValueAsString(user);
            rv = new RedirectView("authorization");
//        mav.addObject("user",json);
        } else {
            rv = new RedirectView("registration");
            rv.addStaticAttribute("error", "Login is busy");
        }
        rv.setExposeModelAttributes(false);
        rv.addStaticAttribute("user", json);
        return rv;
    }
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String signup(ModelMap model, HttpServletRequest request) throws UnknownHostException {
        return "/registration";
    }

    @RequestMapping(value = "confirmation{token}", method = RequestMethod.GET)
    public ModelAndView confirmation(@RequestParam("token") String token){
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.validateToken(token);
        user.setVerification("verificated");
        userService.updateUser(user);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

}