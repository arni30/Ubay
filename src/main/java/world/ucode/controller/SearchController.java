package world.ucode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import world.ucode.models.Search;

@Controller
public class SearchController {
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search_post(@ModelAttribute("search") Search search, Model model) {
        System.out.println(search.getTitle());
        System.out.println(search.getStartPrice());
        System.out.println(search.getDuration());
        System.out.println(search.getStartTime());
        System.out.println(search.getDescription());

        return "redirect:/main";
    }

}
