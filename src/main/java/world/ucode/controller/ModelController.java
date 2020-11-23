package world.ucode.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import world.ucode.models.Lot;
import world.ucode.models.User;
import world.ucode.services.LotService;
import world.ucode.utils.SendMail;
import world.ucode.utils.Token;
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

    /**
     * requires unique user login, which profile needs to be showed. --------- DONE ----------
     * */
    @RequestMapping(value = "/viewProfile", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView viewProfile(@RequestParam String login) {
        ModelAndView mav = new ModelAndView();
        try {
            ObjectMapper mapper = new ObjectMapper();
            User user = userService.findUser(login);
            String json = mapper.writeValueAsString(user);
            mav.addObject("user", json);
            mav.setViewName("/viewProfile");
            return mav;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Bad JSON");
            mav.setViewName("/errors/error");
            return mav;
        }
    }
    @RequestMapping(value = "/feedbacks", method = RequestMethod.GET)
    public String feedbacks(ModelMap model) {
        return "/feedbacks";
    }
    /**
     * requires unique lot id, to what lot add feedback.
     * */
    @RequestMapping(value = "/addFeedback", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView addFeedback(@RequestParam int lotId) {
        ModelAndView mav = new ModelAndView();
        try {
            ObjectMapper mapper = new ObjectMapper();
            Lot lot = userService.findLotById(lotId);
            String json = mapper.writeValueAsString(lot);
            mav.addObject("lot", json);
            mav.setViewName("/addFeedback");
            return mav;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Bad JSON");
            mav.setViewName("/errors/error");
            return mav;
        }
    }
    /**
     * requires unique lot id.
     * */
    @RequestMapping(value = "/auction", method = RequestMethod.GET)
    @ResponseBody
    public String auction(@RequestParam int lotId) {
        return "/auction";
    }

    /**
     * requires unique seller login (what seller added lot).
     * */
    @RequestMapping(value = "/addLot", method = RequestMethod.GET)
    public ModelAndView addLot(@RequestParam String login) {
        return pageModelAndView(login, "/addLot");
    }
    /**
     * ----------------------- helpful functional
     */
    private ModelAndView pageModelAndView(String login, String page) {
        ModelAndView mav = new ModelAndView();
        System.out.println(login);
        try {
            ObjectMapper mapper = new ObjectMapper();
            User user = userService.findUser(login);
            String json = mapper.writeValueAsString(user);
            mav.addObject("user", json);
            mav.setViewName(page);
            return mav;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Bad JSON");
            mav.setViewName("/errors/error");
            return mav;
        }
    }

    @RequestMapping(value = "/addLot", method = RequestMethod.POST)
    public ModelAndView addLot(Lot lot) throws JsonProcessingException {
        System.out.println(lot.getDuration());
        System.out.println(lot.getStartPrice());
        System.out.println(lot.getDescription());
//        System.out.println(user.getId());
        lotService.saveLot(lot);
        ModelAndView mav = new ModelAndView();
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(lot);
        mav.addObject("lot", json);
        mav.setViewName("/profile");
        return mav;
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
        ObjectMapper mapper = new ObjectMapper();
//        try {
            if (user.getType().equals("signin")) {
                System.out.println(user.getPassword());
                System.out.println(user.getLogin());
                System.out.println("hallo");
                User newUser = userService.validateUser(user);
                String json = mapper.writeValueAsString(newUser);
                mav.addObject("user", json);
                mav.setViewName("/profile");
            } else {
                Token token = new Token();
                user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
                user.setToken(token.getJWTToken(user.getLogin()));
                sendMail.sendMail(user);
                System.out.println(user.getUserRole());
                System.out.println(user.getPassword());
                System.out.println(user.getEmail());
                System.out.println(user.getLogin());
                System.out.println("hallo");
                userService.saveUser(user);
                String json = mapper.writeValueAsString(user);
                mav.addObject("user",json);
                mav.setViewName("/main");
            }
            return mav;
//        } catch (Exception e) {
//            System.out.println("NON authorized or incorrect mail");
//            mav.setViewName("/authorization");
//            return mav;
//        }
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