package world.ucode.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import world.ucode.models.Bid;
import world.ucode.models.User;
import world.ucode.services.BidService;
import world.ucode.services.LotService;
import world.ucode.services.UserService;
import org.json.*;

import javax.servlet.http.HttpServletRequest;
@Controller
public class NewBit {
    //    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    @Autowired
    UserService userService;
    ModelAndView mav = new ModelAndView();
    @Autowired
    BidService bidService;
    LotService lotService = new LotService();
    @RequestMapping(value = "/newBit", method = RequestMethod.POST)
    public ModelAndView newBid(HttpServletRequest request, @RequestBody JSONObject json) throws JsonProcessingException {
        Bid bid = new Bid();
        bid.setPrice(Integer.parseInt(json.get("price").toString()));
        User bidder = userService.findUser(request.getUserPrincipal().getName());
        bid.setLot(lotService.findLot(Integer.parseInt(json.get("lotId").toString())));
        bid.setActive(true);
        bidder.addBid(bid);
//        bid.setBidder(bidder);
        bidService.saveBid(bid);

        mav.setViewName("redirect:/main"); // отета все одно не працює
        return mav;
    }
}
