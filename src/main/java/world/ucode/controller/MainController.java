package world.ucode.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import world.ucode.models.Lot;
import world.ucode.models.Search;
import world.ucode.services.LotService;
import world.ucode.utils.CreateJSON;

import java.util.List;
@Controller
public class MainController {
    final private LotService lotService = new LotService();
    final private CreateJSON createJSON = new CreateJSON();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "/index";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView main(ModelMap model) {
        if (!model.containsAttribute("search")) {
            model.addAttribute("search", new Search());
        }
        ModelAndView mav = new ModelAndView();
        try {
            ObjectMapper mapper = new ObjectMapper();
//            Lot lot = userService.findLotById(Integer.parseInt(lotId));
//            String json = mapper.writeValueAsString(lot);

            List<Lot> lots = lotService.findAllLots();
            JSONArray json = createJSON.mainShowLotsJSON(lots);

            mav.addObject("lots", json);
            mav.setViewName("/main");

            return mav;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Bad JSON");
            mav.setViewName("/errors/error");
            return mav;
        }
    }
}
