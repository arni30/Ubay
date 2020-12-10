package world.ucode.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import world.ucode.models.Bid;
import world.ucode.models.User;
import world.ucode.services.BidService;
import world.ucode.services.LotService;
import world.ucode.services.UserService;

import javax.servlet.http.HttpServletRequest;
@Controller
public class AddBid {
    @Autowired
    private UserService userService;
    @Autowired
    private BidService bidService;
    @Autowired
    private LotService lotService;

    @RequestMapping(value = "/newBit", method = RequestMethod.POST)
    public ModelAndView newBid(HttpServletRequest request, @RequestBody JSONObject json){
        ModelAndView mav = new ModelAndView();
        Bid bid = new Bid();
        bid.setPrice(Double.parseDouble(json.get("price").toString()));
        User bidder = userService.findUser(request.getUserPrincipal().getName());
        bid.setLot(lotService.findLot(Integer.parseInt(json.get("lotId").toString())));
        bid.setActive(true);
        bid.setBidder(bidder);
        bidService.saveBid(bid);

        mav.setViewName("redirect:/main");
        return mav;
    }
}
