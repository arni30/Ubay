package world.ucode.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ModelAndView;
import world.ucode.models.Bid;
import world.ucode.models.Lot;
import world.ucode.models.User;
import world.ucode.services.BidService;
import world.ucode.services.LotService;
import world.ucode.services.UserService;

import java.util.List;
import java.util.Set;

public class PageModelAndView {
    @Autowired
    private CreateJSON createJSON;
    @Autowired
    UserService userService;
    @Autowired
    private LotService lotService;
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
            mav.setViewName("/errors/error");
            return mav;
        }
    }
    public ModelAndView pageModelAndView(Lot lot, String page) {
        ModelAndView mav = new ModelAndView();
        try {
            JSONObject json = CreateJSON.addFeedbackJSON(lot);
            mav.addObject("lot", json);
            mav.setViewName(page);
            return mav;
        } catch (Exception e) {
            mav.setViewName("/errors/error");
            return mav;
        }
    }

    public ModelAndView pageModelAndView(String login) throws JsonProcessingException {
        ModelAndView mav = new ModelAndView();
        List<Lot> lots;
        if (login != null && !login.equals("")) {
            ObjectMapper mapper = new ObjectMapper();
            User user = userService.findUserByLogin(login);
            String json = mapper.writeValueAsString(user);
            mav.addObject("user", json);

            lots = lotService.findAllLotsByUser(login);
            Set<Bid> bids = userService.findBidsByBidder(login);

            JSONArray jsonLotsSeller = createJSON.mainShowLotsJSON(lots);
            JSONArray jsonLotsBidder = createJSON.profileBidderShowLotsJSON(bids);
            mav.addObject("lots", user.getUserRole().equals("seller") ? jsonLotsSeller : jsonLotsBidder);
        }
        return mav;
    }
}
