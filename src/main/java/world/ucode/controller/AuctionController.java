package world.ucode.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import world.ucode.models.Bid;
import world.ucode.models.Feedback;
import world.ucode.models.Lot;
import world.ucode.models.User;
import world.ucode.services.BidService;
import world.ucode.services.FeedbackService;
import world.ucode.services.UserService;
import world.ucode.utils.CreateJSON;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuctionController {
    @Autowired
    UserService userService;
    @Autowired
    BidService bidService;
    @Autowired
    FeedbackService feedbackService;
    @Autowired
    CreateJSON createJSON;
    /**
     * requires unique lot id (that auction show).
     * */
    @RequestMapping(value = "/auction", method = RequestMethod.GET)
    public ModelAndView auction(@RequestParam String lotId, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        try {
            int LotId = Integer.parseInt(lotId);
            Lot lot = userService.findLotById(LotId);
            User user = lot.getSeller();
            JSONObject json = createJSON.auctionJSON(user, lot);
            mav.addObject("lot", json);
            try {
                System.out.println("BID");
                Bid lastBid = bidService.findLast(LotId);
                if (lastBid != null) {
                    System.out.println("IF");
                    String description = "";
                    Feedback feedback = feedbackService.findFeedbackByLot(LotId);
                    if (feedback != null)
                        description = feedback.getDescription();
                    JSONObject winnerJson = createJSON.winnerJSON(lastBid, description, true);
                    mav.addObject("winner", winnerJson);
                }
                else {
                    JSONObject winnerJson = createJSON.winnerJSON(null, null, false);
                    mav.addObject("winner", winnerJson);//redo!!!!
                }
            } catch (Exception ignored) {
                System.out.println("problem with bids");
            }
            if (request.getUserPrincipal() != null)
                user = userService.findUserByLogin(request.getUserPrincipal().getName());
            mav.addObject("userType", user.getUserRole());
            mav.setViewName("/auction");
            return mav;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Bad JSON");
            mav.setViewName("/errors/error");
            return mav;
        }
    }
}
