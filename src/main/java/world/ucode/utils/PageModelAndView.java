package world.ucode.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import world.ucode.models.Bid;
import world.ucode.models.Feedback;
import world.ucode.models.Lot;
import world.ucode.models.User;
import world.ucode.services.BidService;
import world.ucode.services.FeedbackService;
import world.ucode.services.LotService;
import world.ucode.services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

public class PageModelAndView {
    @Autowired
    private CreateJSON createJSON;
    @Autowired
    private UserService userService;
    @Autowired
    private LotService lotService;
    @Autowired
    private BidService bidService;
    @Autowired
    private FeedbackService feedbackService;

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

            lots = lotService.findAllLotsByUser(login);
            Set<Bid> bids = userService.findBidsByBidder(login);

            JSONArray jsonLotsSeller = createJSON.mainShowLotsJSON(lots);
            JSONArray jsonLotsBidder = createJSON.profileBidderShowLotsJSON(bids);
            mav.addObject("lots", user.getUserRole().equals("seller") ? jsonLotsSeller : jsonLotsBidder);
            mav.addObject("user", json);
        }
        return mav;
    }

    public ModelAndView pageModelAndView(int LotId, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        Lot lot = userService.findLotById(LotId);
        User user = lot.getSeller();
        JSONObject json = createJSON.auctionJSON(user, lot);
        Bid lastBid = bidService.findLast(LotId);

        if (lastBid != null) {
            String description = "";
            Feedback feedback = feedbackService.findFeedbackByLot(LotId);
            if (feedback != null)
                description = feedback.getDescription();
            JSONObject winnerJson = createJSON.winnerJSON(lastBid, description, true);
            mav.addObject("winner", winnerJson);
        }
        else {
            JSONObject winnerJson = createJSON.winnerJSON(null, null, false);
            mav.addObject("winner", winnerJson);
        }
        if (request.getUserPrincipal() != null)
            user = userService.findUserByLogin(request.getUserPrincipal().getName());
        mav.addObject("userType", user.getUserRole());
        mav.addObject("lot", json);
        mav.setViewName("/auction");
        return mav;
    }

    public ModelAndView pageModelAndViewFeedback(String login) {
        ModelAndView mav = new ModelAndView();
        List<Feedback> fs = feedbackService.findAllByUser(login);
        JSONArray json = createJSON.feedbacksJSON(fs);
        JSONObject sellerInfo = createJSON.sellerInfoJson(login);

        mav.addObject("sellerInfo", sellerInfo);
        mav.addObject("fs", json);
        mav.setViewName("/feedbacks");
        return mav;
    }
}
