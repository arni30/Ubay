package world.ucode.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import world.ucode.models.Lot;
import world.ucode.models.User;
import world.ucode.services.LotService;
import world.ucode.services.SendMail;
import world.ucode.services.Token;
import world.ucode.services.UserService;
import java.net.UnknownHostException;

@Controller
@ControllerAdvice
public class ModelController {
    SendMail sendMail = new SendMail();
    LotService lotService = new LotService();
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
    @RequestMapping(value = "/authorization", method = RequestMethod.GET)
    public String signin(ModelMap model) throws UnknownHostException {
//        model.addAttribute("form", new User());
        return "/authorization";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(ModelMap model) {
        return "/main";
    }
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(ModelMap model) {
        return "/profile";
    }
    @RequestMapping(value = "/feedbacks", method = RequestMethod.GET)
    public String feedbacks(ModelMap model) {
        return "/feedbacks";
    }
    @RequestMapping(value = "/addLot", method = RequestMethod.GET)
    public String addLot(ModelMap model) {
        return "/addLot";
    }

    @RequestMapping(value = "confirmation{token}", method = RequestMethod.GET)
    public ModelAndView confirmation(@RequestParam("token") String token){
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.validateToken(token);
        user.setVerification("verificated");
        userService.updateUser(user);
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }
    @RequestMapping(value = "/authorization", method = RequestMethod.POST)
    public ModelAndView signin_post(User user, ModelMap model) throws Exception {
        System.out.println(user.getType());
        ModelAndView mav = new ModelAndView();
//        try {
            if (user.getType().equals("signin")) {
                System.out.println(user.getPassword());
                System.out.println(user.getLogin());
                System.out.println("hallo");
                ObjectMapper mapper = new ObjectMapper();
                User newUser = userService.validateUser(user);
                String json = mapper.writeValueAsString(newUser);
                mav.addObject("user", json);
                mav.setViewName("/profile");
            } else {
                Token token = new Token();
                user.setToken(token.getJWTToken(user.getLogin()));
                sendMail.sendMail(user);
                System.out.println(user.getUserRole());
                System.out.println(user.getPassword());
                System.out.println(user.getEmail());
                System.out.println(user.getLogin());
                System.out.println("hallo");
                model.addAttribute("user",user);
                userService.saveUser(user);
                mav.setViewName("/main");
            }
            return mav;
//        } catch (Exception e) {
//            System.out.println("NON authorized or incorrect mail");
//            mav.setViewName("/authorization");
//            return mav;
//        }
    }

    @RequestMapping(value = "/addLot", method = RequestMethod.POST)
    public ModelAndView addLot(Lot lot) throws JsonProcessingException {
        System.out.println(lot.getDuration());
        System.out.println(lot.getStartPrice());
        System.out.println(lot.getDescription());
        lotService.saveLot(lot);
        ModelAndView mav = new ModelAndView();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(lot);
        mav.addObject("lot", json);
        mav.setViewName("/profile");
        return mav;
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

}