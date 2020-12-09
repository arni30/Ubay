package world.ucode.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import world.ucode.models.User;
import world.ucode.security.AuthProvider;
import world.ucode.services.UserService;
import world.ucode.utils.SendMail;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;
import java.util.Collection;

@Controller
public class ChangePersonalInfoController {
    @Autowired
    UserService userService;
    @Autowired
    AuthProvider authProvider;
    @RequestMapping(value = "/changePersonalInfo", method = RequestMethod.POST)
    public String changeProfile(@RequestBody JSONObject json,
                                HttpServletRequest httpServletRequest) throws UnknownHostException {
        System.out.println("Auth");
        System.out.println(httpServletRequest.getUserPrincipal().getName());
        System.out.println("Auth");
        User newUser = userService.findUser(httpServletRequest.getUserPrincipal().getName());
        newUser.setEmail(json.get("newEmail").toString());
        newUser.setBalance(Double.parseDouble(json.get("newBalance").toString()));
        userService.updateUser(newUser);
        return "profile";
    }
}
