package world.ucode.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
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
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;
    @Autowired
    private SendMail sendMail;

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
        } else {
            rv = new RedirectView("registration");
            rv.addStaticAttribute("error", "Login is busy");
        }
        rv.setExposeModelAttributes(false);
        rv.addStaticAttribute("user", json);
        return rv;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String signup() {
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
