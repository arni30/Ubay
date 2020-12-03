package world.ucode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
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
public class ChangeProfileController {
//    @Autowired
//    private SendMail sendMail;
    @Autowired
    UserService userService;
    @Autowired
    AuthProvider authProvider;
    @RequestMapping(value = "/changeProfile", method = RequestMethod.GET)
    public String changeProfile(User user, HttpServletRequest httpServletRequest) throws UnknownHostException {
        System.out.println(user.getLogin());
        System.out.println("Auth");
        System.out.println(httpServletRequest.getUserPrincipal().getName());
        System.out.println("Auth");
        User newUser = userService.findUser(httpServletRequest.getUserPrincipal().getName());
        newUser.setLogin(user.getLogin());
        newUser.setEmail(user.getEmail());
        newUser.setBalance(user.getBalance());
        userService.updateUser(newUser);
        Collection<? extends GrantedAuthority> authorities = newUser.getAuthorities();
        Authentication request = new UsernamePasswordAuthenticationToken(newUser.getLogin(), newUser.getPassword(), authorities);
        Authentication result = authProvider.authenticate(request);
        SecurityContextHolder.getContext().setAuthentication(result);
        return "profile";
    }
//    http://192.168.0.106:8080/ubay/changeProfile?login=2&email=arni@gmail.com&balance=101
}
