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
    FeedbackService feedbackService = new FeedbackService();

    CreateJSON createJSON = new CreateJSON();
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
            if (!lot.getActive()) {
                try {
                    Bid lastBid = bidService.findLast(LotId);
                    String description = "";
                    Feedback feedback = feedbackService.findFeedbackByLot(LotId);
                    if (feedback != null)
                        description = feedback.getDescription();
                    JSONObject winnerJson = createJSON.winnerJSON(lastBid, description);
                    mav.addObject("winner", winnerJson);
                } catch (Exception ignored) {}
            }
            else
                mav.addObject("winner", json);//redo!!!!
            if (request.getUserPrincipal() != null) {
                user = userService.findUserByLogin(request.getUserPrincipal().getName());
            }
            mav.addObject("userType", user.getUserRole());
            mav.addObject("lot", json);
            if (request.getUserPrincipal() != null) {
                user = userService.findUserByLogin(request.getUserPrincipal().getName());
            }
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
