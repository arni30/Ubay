package world.ucode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import world.ucode.utils.PageModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuctionController {
    @Autowired
    private PageModelAndView pageModelAndView;

    /**
     * requires unique lot id (that auction show).
     * */
    @RequestMapping(value = "/auction", method = RequestMethod.GET)
    public ModelAndView auction(@RequestParam String lotId, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        try {
            return pageModelAndView.pageModelAndView(Integer.parseInt(lotId), request);
        } catch (Exception e) {
            mav.setViewName("/errors/error");
            return mav;
        }
    }
}
