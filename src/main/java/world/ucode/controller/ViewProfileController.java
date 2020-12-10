package world.ucode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import world.ucode.utils.PageModelAndView;

@Controller
public class ViewProfileController {
    @Autowired
    private PageModelAndView pageModelAndView;

    @RequestMapping(value = "/viewProfile", method = RequestMethod.GET)
    public ModelAndView viewProfile(@RequestParam String login) {
        ModelAndView mav;
        try {
            mav = pageModelAndView.pageModelAndView(login);
            mav.setViewName("/viewProfile");
            return mav;
        } catch (Exception e) {
            mav = new ModelAndView();
            mav.setViewName("/errors/error");
            return mav;
        }
    }
}
