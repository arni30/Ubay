package world.ucode.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import world.ucode.models.User;
import world.ucode.security.AuthProvider;
import world.ucode.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Collection;

@Controller
public class ChangePasswordController {
    @Autowired
    UserService userService;
    @Autowired
    AuthProvider authProvider;
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public String changePassword(@RequestBody JSONObject json,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse response) throws IOException {
        User newUser = userService.findUser(httpServletRequest.getUserPrincipal().getName());
        if(BCrypt.checkpw(json.get("oldPassword").toString(), newUser.getPassword())) {
            newUser.setPassword(BCrypt.hashpw(json.get("newPassword").toString(), BCrypt.gensalt()));
            userService.updateUser(newUser);
//            Collection<? extends GrantedAuthority> authorities = newUser.getAuthorities();
//            Authentication request = new UsernamePasswordAuthenticationToken(newUser.getLogin(), newUser.getPassword(), authorities);
//            Authentication result = authProvider.authenticate(request);
//            SecurityContextHolder.getContext().setAuthentication(result);
        }
        else
            response.sendError(401);
        return "profile";
    }
}
