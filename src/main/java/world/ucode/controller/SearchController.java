package world.ucode.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import world.ucode.models.Lot;
import world.ucode.models.Search;
import world.ucode.services.LotService;
import world.ucode.utils.CreateJSON;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {
    @Autowired
    private LotService lotService;
    CreateJSON createJSON = new CreateJSON();

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public void search_post(@RequestBody JSONObject json) {
        String title = json.get("title") != "" ?
                json.get("title").toString() : null;
        double price = json.get("price") != "" ?
                Double.parseDouble(json.get("price").toString()) : 0;
        int duration = json.get("duration") != "" ?
                Integer.parseInt(json.get("duration").toString()) : 0;
        String startTime = json.get("startTime") != "" ?
                json.get("startTime").toString() : null;
        String description = json.get("description") != "" ?
                json.get("description").toString() : null;

        List<Lot> lots = lotService.findAllLots();
        List<Lot> newList = new ArrayList<>();
        boolean isValid;

        for (Lot l : lots) {
            isValid = true;
            if (title != null) {
                isValid = l.getTitle().contains(title);
            }
            if (isValid && price != 0) {
                isValid = l.getStartPrice() == price;
            }
            if (isValid && duration != 0) {
                isValid = l.getDuration() == duration;
            }
            if (isValid && startTime != null) {
                isValid = l.getStartTime().toString().contains(startTime);
            }
            if (isValid && description != null) {
                isValid = l.getDescription().contains(description);
            }
            if (isValid) {
                newList.add(l);
            }
        }
        for (Lot l : newList) {
            System.out.println(l.getTitle());
        }
//        response.addHeader("json", createJSON.mainShowLotsJSON(newList).toJSONString());

//        return createJSON.mainShowLotsJSON(newList);
    }

}
