package world.ucode.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ModelAndView;
import world.ucode.models.User;
import world.ucode.services.UserService;

public class PageModelAndView {
    UserService userService = new UserService();

    /**
     * ----------------------- helpful functional
     */


    public ModelAndView pageModelAndView(String login, String page) {
        ModelAndView mav = new ModelAndView();
        System.out.println(login);
        try {
            ObjectMapper mapper = new ObjectMapper();
            User user = userService.findUserByLogin(login);
            String json = mapper.writeValueAsString(user);
            mav.addObject("user", json);
            mav.setViewName(page);
            return mav;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Bad JSON");
            mav.setViewName("/errors/error");
            return mav;
        }
    }
    public ModelAndView pageModelAndView(int lotId, String page) {
        ModelAndView mav = new ModelAndView();
        System.out.println(lotId);
        try {
            ObjectMapper mapper = new ObjectMapper();
//            Lot lot = userService.findLotById(lotId);
//            String json = mapper.writeValueAsString(lot);
//            mav.addObject("lot", json);
            mav.setViewName(page);
            return mav;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Bad JSON");
            mav.setViewName("/errors/error");
            return mav;
        }
    }
}
