package world.ucode.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
import world.ucode.services.LotService;
import world.ucode.services.UserService;
import world.ucode.utils.PageModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class FeedbacksController {
    @Autowired
    private UserService userService;
    @Autowired
    private BidService bidService;
    @Autowired
    private LotService lotService;
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private PageModelAndView pageModelAndView;
    /**
     * requires unique seller login (feedbacks about what seller).
     * */
    @RequestMapping(value = "/feedbacks", method = RequestMethod.GET)
    public ModelAndView feedbacks(@RequestParam String login) {
        ModelAndView mav = new ModelAndView();

        try {
            return pageModelAndView.pageModelAndViewFeedback(login);
        } catch (Exception e) {
            mav.setViewName("/errors/error");
            return mav;
        }
    }

    /**
     * requires unique lot id (to what lot was added feedback).
     * */
    @RequestMapping(value = "/addFeedback", method = RequestMethod.GET)
    public ModelAndView addFeedback(@RequestParam String lotId, HttpServletRequest request) {
        Lot lot = lotService.findLot(Integer.parseInt(lotId));
        Bid lastBid = bidService.findLast(lot.getId());
        ModelAndView mav = new ModelAndView();

        if (lastBid != null && !lot.getActive() && lastBid.getBidder().getLogin().equals(request.getUserPrincipal().getName())) {
            return pageModelAndView.pageModelAndView(
                    lotService.findLot(Integer.parseInt(lotId)), "/addFeedback");
        }
        mav.setViewName("redirect:/main");
        return mav;
    }

    @RequestMapping(value = "/addFeedback", method = RequestMethod.POST)
    public String addFeedbackPost(HttpServletRequest request, @RequestBody JSONObject json) {
        Feedback feedback = new Feedback();
        Lot lot = lotService.findLot(Integer.parseInt(json.get("lotId").toString()));
        User seller = lot.getSeller();

        feedback.setBidder(userService.findUser(request.getUserPrincipal().getName()));
        feedback.setSeller(seller);
        feedback.setDescription(json.get("description").toString());
        feedback.setRate(Double.parseDouble(json.get("rate").toString()));
        feedback.setLot(lot);
        feedbackService.saveFeedback(feedback);
        seller.setAvarageRate(CountRate(seller.getUsername()));
        userService.updateUser(seller);
        return "redirect:/auction?lotId="+json.get("lotId");
    }

    public float CountRate(String login) {
        List<Feedback> list = feedbackService.findAllByUser(login);
        if (list == null)
            return 0;
        float rates = 0;
        float count = list.size();
        for ( Feedback feedback : list)
            rates += feedback.getRate();
        return rates/count;
    }
}
