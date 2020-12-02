package world.ucode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import world.ucode.utils.PageModelAndView;

@Controller
public class FeedbacksController {
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
}
