package world.ucode.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class NewBit {
    //    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    @Autowired
    UserService userService;
    ModelAndView mav = new ModelAndView();
    BidService bidService = new BidService();
    LotService lotService = new LotService();
    @RequestMapping(value = "/newBit", method = RequestMethod.POST)
    public ModelAndView newBid(Bid bid, HttpServletRequest request) throws JsonProcessingException {
        System.out.println(bid.getPrice());
        User bidder = userService.findUser(request.getUserPrincipal().getName());
        bid.setLot(lotService.findLot(1));
        bid.setActive(true);
        bidder.addBid(bid);
//        bid.setBidder(bidder);
        bidService.saveBid(bid);
        mav.setViewName("redirect:/main");
        return mav;
    }
}
