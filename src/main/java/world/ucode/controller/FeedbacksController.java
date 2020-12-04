package world.ucode.controller;

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
import world.ucode.utils.PageModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FeedbacksController {
    @Autowired
    UserService userService;
    @Autowired
    BidService bidService;
    LotService lotService = new LotService();
    FeedbackService feedbackService = new FeedbackService();
    final private PageModelAndView pageModelAndView = new PageModelAndView();
    /**
     * requires unique seller login (feedbacks about what seller).
     * */
    @RequestMapping(value = "/feedbacks", method = RequestMethod.GET)
    public ModelAndView feedbacks(@RequestParam String login) {
        return pageModelAndView.pageModelAndView(login, "/feedbacks");
    }

    /**
     * requires unique lot id (to what lot was added feedback).
     * */
    @RequestMapping(value = "/addFeedback", method = RequestMethod.GET)
    public ModelAndView addFeedback(@RequestParam String lotId) {
        return pageModelAndView.pageModelAndView(Integer.parseInt(lotId), "/addFeedback");
    }

    @RequestMapping(value = "/addFeedback", method = RequestMethod.POST)
    public String addFeedbackPost(HttpServletRequest request, @RequestBody JSONObject json) {
        Feedback feedback = new Feedback();
        feedback.setSeller(userService.findUser(request.getUserPrincipal().getName()));
        feedback.setDescription(json.get("description").toString());
        feedback.setRate(Integer.parseInt(json.get("rate").toString()));
        feedback.setLot(lotService.findLot(Integer.parseInt(json.get("lotId").toString())));
        feedbackService.saveFeedback(feedback);
        return "redirect:/main";
    }
}
