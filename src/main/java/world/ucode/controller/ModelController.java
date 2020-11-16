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
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    UserService userService = context.getBean("userService", UserService.class);
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "/index";
    }

    @RequestMapping(value = "/hallo{id}", produces = "text/plain;charset=UTF-8", method = RequestMethod.GET)
    public String developer(@RequestParam int id, ModelMap model) {
        model.addAttribute("id", id);
        System.out.println(id);
//        database();
        return "/id";
    }

    // -----------------------
    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public String signin(ModelMap model) {
//        model.addAttribute("form", new User());
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


    @RequestMapping(value = "/signin/signup", method = RequestMethod.POST)
    public String signup_post(User user, ModelMap model) {
//        System.out.println(usr.getType());
        System.out.println(user.getUserRole());
        System.out.println(user.getPassword());
        System.out.println(user.getEmail());
        System.out.println(user.getLogin());
        System.out.println("hallo");
        model.addAttribute("user",user);
        userService.saveUser(user);
        return "about";
    }
    @RequestMapping(value = "/signin/signin", method = RequestMethod.POST)
    public String signin_post(User user, ModelMap model) {
        System.out.println(user.getPassword());
        System.out.println(user.getLogin());
        System.out.println("hallo");
        try {
            User newUser = userService.validateUser(user);
            System.out.println(newUser.getEmail());
            model.addAttribute("user",newUser);
            return "/about";
        }
        catch (Exception e){
            System.out.println("EXCEPTION VALIDATION");
        }
        return "/signin";
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

    private void databaseClose(User user) {
        userService.updateUser(user);
        context.close();
    }
//    private void database() {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        UserService userService = context.getBean("userService", UserService.class);
//
//        User user = new User("tro", "qwerty", "seller");
//        userService.saveUser(user);
//
//        Lot ferrari = new Lot("Ferrari", 12000);
//        ferrari.setSeller(user);
//        user.addLot(ferrari);
//        Lot ford = new Lot("Ford", 600);
//        ford.setSeller(user);
//        user.addLot(ford);
//        userService.updateUser(user);
//        context.close();
//    }
}