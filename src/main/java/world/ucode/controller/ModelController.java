package world.ucode.controller;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.DefaultSessionAttributeStore;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartResolver;
import world.ucode.models.Lot;
import world.ucode.models.User;
import world.ucode.services.UserService;

import java.util.Map;

@Controller
@ControllerAdvice
public class ModelController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "/index";
    }

    @RequestMapping(value = "/hallo{id}", produces = "text/plain;charset=UTF-8", method = RequestMethod.GET)
    public String developer(@RequestParam int id, ModelMap model) {
        model.addAttribute("id", id);
        System.out.println(id);
        database();
        return "/id";
    }

    // -----------------------
    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String signin(ModelMap model) {
        model.addAttribute("form", new Usr());
        return "/signin";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(ModelMap model) {
        return "/main";
    }
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(ModelMap model) {
        return "/profile";
    }
    @RequestMapping(value = "/addLot", method = RequestMethod.GET)
    public String addLot(ModelMap model) {
        return "/addLot";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    @ResponseBody
    public void signup_post(@RequestParam Map<String, String> requestParams) {
//        public void signup_post(@ModelAttribute("form") Usr usr, ModelMap model) {
//        System.out.println(usr.getType());
//        System.out.println(usr.getType());
//        System.out.println(usr.getPassword());
//        System.out.println(usr.getConfirmpassword());
//        System.out.println(usr.getEmail());
//        System.out.println(usr.getUsername());
//        System.out.println(usr.toString());

        System.out.println(requestParams.toString());
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