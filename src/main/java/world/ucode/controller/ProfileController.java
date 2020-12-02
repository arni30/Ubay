package world.ucode.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import world.ucode.models.Lot;
import world.ucode.models.User;
import world.ucode.services.LotService;
import world.ucode.services.UserService;
import world.ucode.utils.CreateJSON;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ProfileController {
    final private LotService lotService = new LotService();
    final private CreateJSON createJSON = new CreateJSON();
    @Autowired
    UserService userService;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView profile(ModelMap model, HttpServletRequest request) {
        String login = request.getUserPrincipal().getName();
        ModelAndView mav = new ModelAndView();
        try {
            if (login != null && !login.equals("")) {
                ObjectMapper mapper = new ObjectMapper();
                User user = userService.findUserByLogin(login);
                String json = mapper.writeValueAsString(user);
                mav.addObject("user", json);

                List<Lot> lots = lotService.findAllLotsByUser(login);
                JSONArray jsonArr = createJSON.mainShowLotsJSON(lots);
                mav.addObject("lots", jsonArr);
            }
            mav.setViewName("/profile");
            return mav;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Bad JSON");
            mav.setViewName("/errors/error");
            return mav;
        }
    }
}
