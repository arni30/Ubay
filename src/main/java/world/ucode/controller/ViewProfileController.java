package world.ucode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import world.ucode.utils.PageModelAndView;

@Controller
public class ViewProfileController {
    PageModelAndView pageModelAndView = new PageModelAndView();
    @RequestMapping(value = "/viewProfile", method = RequestMethod.GET)
    public ModelAndView viewProfile(@RequestParam String login) {
        return pageModelAndView.pageModelAndView(login, "/viewProfile");
    }
}
