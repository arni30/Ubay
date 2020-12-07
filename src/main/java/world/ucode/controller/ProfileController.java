package world.ucode.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import world.ucode.models.Lot;
import world.ucode.models.User;
import world.ucode.services.BidService;
import world.ucode.services.LotService;
import world.ucode.services.UserService;
import world.ucode.utils.CreateJSON;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Controller

public class ProfileController {
    @Autowired
    private CreateJSON createJSON;
    @Autowired
    UserService userService;
    @Autowired
    private LotService lotService;
    @Autowired
    private BidService bidService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profile(ModelMap model, HttpServletRequest request) {
        String login = request.getUserPrincipal().getName();
        ModelAndView mav = new ModelAndView();
        try {
            if (login != null && !login.equals("")) {
                ObjectMapper mapper = new ObjectMapper();
                User user = userService.findUserByLogin(login);
                String json = mapper.writeValueAsString(user);
                mav.addObject("user", json);

                List<Lot> lots = lotService.findAllLotsByUser(login);
//                List<Bid> bids = bidService.findAllBidsByUser(login);
                System.out.println("----------------");

//                System.out.println(user.getBids());
//                if (user.getUserRole() == "bidder") {
//                    HashMap<Lot, Bid> b = user.getListOfBiddersLastBids();
//                    System.out.println(b);
//                }

                JSONArray jsonArr = createJSON.mainShowLotsJSON(lots);
                mav.addObject("lots", jsonArr);
            }
            mav.setViewName("/profile");
            return mav;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Bad JSON");
            mav.setViewName("/errors/error");
            return mav;
        }
    }
    @RequestMapping(value = "/changePersonalInfo", method = RequestMethod.POST)
    public ModelAndView changePersonalInfo(HttpServletRequest request, @RequestBody JSONObject json) {
//        String login = request.getUserPrincipal().getName();

        System.out.println(json.get("newEmail"));
        System.out.println(json.get("newBalance"));

        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/profile"); // отета якось не працює
        return new ModelAndView();
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public ModelAndView changePassword(HttpServletRequest request, @RequestBody JSONObject json) {
//        String login = request.getUserPrincipal().getName();

        System.out.println(json.get("oldPassword"));
        System.out.println(json.get("newPassword"));

        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/profile"); // отета якось не працює
        return new ModelAndView();
    }
}
