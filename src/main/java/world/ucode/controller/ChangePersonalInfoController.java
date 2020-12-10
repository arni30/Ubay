package world.ucode.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import world.ucode.models.User;
import world.ucode.security.AuthProvider;
import world.ucode.services.UserService;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ChangePersonalInfoController {
    @Autowired
    UserService userService;
    @Autowired
    AuthProvider authProvider;
    @RequestMapping(value = "/changePersonalInfo", method = RequestMethod.POST)
    public String changeProfile(@RequestBody JSONObject json,
                                HttpServletRequest httpServletRequest){
        User newUser = userService.findUser(httpServletRequest.getUserPrincipal().getName());
        newUser.setEmail(json.get("newEmail").toString());
        newUser.setBalance(Double.parseDouble(json.get("newBalance").toString()));
        userService.updateUser(newUser);
        return "profile";
    }
}
