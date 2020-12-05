package world.ucode.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import world.ucode.models.Feedback;
import world.ucode.services.BidService;
import world.ucode.services.FeedbackService;
import world.ucode.services.LotService;
import world.ucode.services.UserService;
import world.ucode.utils.CreateJSON;
import world.ucode.utils.PageModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class FeedbacksController {
    @Autowired
    UserService userService;
    @Autowired
    BidService bidService;
    LotService lotService = new LotService();
    FeedbackService feedbackService = new FeedbackService();
    CreateJSON createJSON = new CreateJSON();
    final private PageModelAndView pageModelAndView = new PageModelAndView();
    /**
     * requires unique seller login (feedbacks about what seller).
     * */
    @RequestMapping(value = "/feedbacks", method = RequestMethod.GET)
    public ModelAndView feedbacks(@RequestParam String login) {
        ModelAndView mav = new ModelAndView();
        try {
            List<Feedback> fs = feedbackService.findAllByUser(login);
            JSONArray json = createJSON.feedbacksJSON(fs);
            mav.addObject("fs", json);

            JSONObject sellerInfo = new JSONObject();
            sellerInfo.put("username", login);
            sellerInfo.put("rate", userService.findUser(login).getAvarageRate());
            mav.addObject("sellerInfo", sellerInfo);

            mav.setViewName("/feedbacks");
            return mav;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Bad JSON");
            mav.setViewName("/errors/error");
            return mav;
        }
    }

    /**
     * requires unique lot id (to what lot was added feedback).
     * */
    @RequestMapping(value = "/addFeedback", method = RequestMethod.GET)
    public ModelAndView addFeedback(@RequestParam String lotId) {
        return pageModelAndView.pageModelAndView(
                lotService.findLot(Integer.parseInt(lotId)), "/addFeedback");
    }

    @RequestMapping(value = "/addFeedback", method = RequestMethod.POST)
    public ModelAndView addFeedbackPost(HttpServletRequest request, @RequestBody JSONObject json) {
        Feedback feedback = new Feedback();
        feedback.setSeller(userService.findUser(request.getUserPrincipal().getName()));
        feedback.setDescription(json.get("description").toString());
        feedback.setRate(Double.parseDouble(json.get("rate").toString()));
        feedback.setLot(lotService.findLot(Integer.parseInt(json.get("lotId").toString())));
        feedbackService.saveFeedback(feedback);

        ModelAndView mav = new ModelAndView();  /// мені здається що це все одно не працює
        mav.setViewName("redirect:/auction?lotId="+json.get("lotId"));
        return mav;
    }
}
