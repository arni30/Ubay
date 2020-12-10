package world.ucode.controller;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private LotService lotService;
    @Autowired
    private CreateJSON createJSON;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "/index";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public ModelAndView main(ModelMap model) {
        if (!model.containsAttribute("search"))
            model.addAttribute("search", new Search());
        ModelAndView mav = new ModelAndView();
        try {
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
