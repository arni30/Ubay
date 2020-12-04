package world.ucode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import world.ucode.utils.SendMail;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;

@Controller
public class ForgotPasswordController {
    @Autowired
    private SendMail sendMail;
    @RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
    public String forgotPassword(@RequestParam("login") String login) throws UnknownHostException {
        sendMail.sendMailPassword(login);
        return "authorization";
    }
}
