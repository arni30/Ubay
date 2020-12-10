package world.ucode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import world.ucode.services.UserService;
import world.ucode.utils.PageModelAndView;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ProfileController {
    @Autowired
    PageModelAndView pageModelAndView;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profile(HttpServletRequest request) {
        String login = request.getUserPrincipal().getName();
        ModelAndView mav;
        try {
            mav = pageModelAndView.pageModelAndView(login);
            mav.setViewName("/profile");
            return mav;
        } catch (Exception e) {
            mav = new ModelAndView();
            e.printStackTrace();
            System.out.println("Bad JSON");
            mav.setViewName("/errors/error");
            return mav;
        }
    }
}
