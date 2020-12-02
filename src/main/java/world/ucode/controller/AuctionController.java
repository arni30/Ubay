package world.ucode.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import world.ucode.models.Lot;
import world.ucode.models.User;
import world.ucode.services.UserService;
import world.ucode.utils.CreateJSON;

@Controller
public class AuctionController {
    @Autowired
    UserService userService;
    CreateJSON createJSON = new CreateJSON();
    /**
     * requires unique lot id (that auction show).
     * */
    @RequestMapping(value = "/auction", method = RequestMethod.GET)
    public ModelAndView auction(@RequestParam String lotId) {
        ModelAndView mav = new ModelAndView();
        try {
            Lot lot = userService.findLotById(Integer.parseInt(lotId));
            User user = lot.getSeller();
            JSONObject json = createJSON.auctionJSON(user, lot);
            mav.addObject("lot", json);
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
