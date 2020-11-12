package world.ucode.controller;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import world.ucode.models.Lot;
import world.ucode.models.User;
import world.ucode.services.UserService;

@Controller
@ControllerAdvice
public class ModelController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "/index";
    }

    @RequestMapping(value="/hallo{id}",  produces = "text/plain;charset=UTF-8", method = RequestMethod.GET)
    public String developer(@RequestParam int id, ModelMap model) {
        model.addAttribute("id", id);
        System.out.println(id);
        database();
        return "/id";
    }

// -----------------------
    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String signin(ModelMap model) {
        model.addAttribute("form", new Usr("1", "2", "3", "4", "5"));
        return "/signin";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public void signup_post(@ModelAttribute("form") Usr usr, final BindingResult bindingResult,
                            final Model model) {

        System.out.println(usr.getPassword());
        System.out.println("hallo");
    }


    
// -----------------------
    @RequestMapping(value = "/errors/404", method = RequestMethod.GET)
    public String error404() {
        return "/errors/404";
    }
    @RequestMapping(value = "/errors/error", method = RequestMethod.GET)
    public String exceptions() {
        return "/errors/error";
    }


    private void database() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        UserService userService = context.getBean("userService", UserService.class);

        User user = new User("tro", "qwerty", 0);
        userService.saveUser(user);

        Lot ferrari = new Lot("Ferrari", 12000);
        ferrari.setSeller(user);
        user.addLot(ferrari);
        Lot ford = new Lot("Ford", 600);
        ford.setSeller(user);
        user.addLot(ford);
        userService.updateUser(user);
        context.close();
    }
}