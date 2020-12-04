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

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuctionController {
    @Autowired
    private UserService userService;
    @Autowired
    private CreateJSON createJSON;
    /**
     * requires unique lot id (that auction show).
     * */
    @RequestMapping(value = "/auction", method = RequestMethod.GET)
    public ModelAndView auction(@RequestParam String lotId, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        try {
            Lot lot = userService.findLotById(Integer.parseInt(lotId));
            User user = lot.getSeller();
            JSONObject json = createJSON.auctionJSON(user, lot);
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
