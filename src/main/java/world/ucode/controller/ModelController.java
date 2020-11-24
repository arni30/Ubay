package world.ucode.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import world.ucode.models.Lot;
import world.ucode.models.Search;
import world.ucode.models.User;
import world.ucode.services.SendMail;
import world.ucode.services.Token;
import world.ucode.services.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@ControllerAdvice
public class ModelController {
    SendMail sendMail = new SendMail();
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    UserService userService = context.getBean("userService", UserService.class);
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "/index";
    }

    @RequestMapping(value = "/hallo", produces = "text/plain;charset=UTF-8", method = RequestMethod.GET)
    public String developer(@RequestParam int id, ModelMap model) {
        model.addAttribute("id", id);
        System.out.println(id);
//        database();
        return "/id";
    }

    /**
     * ----------------------- helpful functional
     */
    private ModelAndView pageModelAndView(String login, String page) {
        ModelAndView mav = new ModelAndView();
        System.out.println(login);
        try {
            ObjectMapper mapper = new ObjectMapper();
            User user = userService.findUserByLogin(login);
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
    private ModelAndView pageModelAndView(int lotId, String page) {
        ModelAndView mav = new ModelAndView();
        System.out.println(lotId);
        try {
            ObjectMapper mapper = new ObjectMapper();
//            Lot lot = userService.findLotById(lotId);
//            String json = mapper.writeValueAsString(lot);
//            mav.addObject("lot", json);
            mav.setViewName(page);
            return mav;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Bad JSON");
            mav.setViewName("/errors/error");
            return mav;
        }
    }

    // -----------------------
    @RequestMapping(value = "/authorization", method = RequestMethod.GET)
    public String signin(ModelMap model) throws UnknownHostException {
//        model.addAttribute("form", new User());
        return "/authorization";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String main(ModelMap model) {
        if(!model.containsAttribute("search")) {
            model.addAttribute("search", new Search());
        }
        return "/main";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(ModelMap model) {
        return "/profile";
    }

    /**
     * requires unique user login, which profile needs to be showed.
     * */
    @RequestMapping(value = "/viewProfile", method = RequestMethod.GET)
    public ModelAndView viewProfile(@RequestParam String login) {
        return pageModelAndView(login, "/viewProfile");
    }
    /**
     * requires unique seller login (feedbacks about what seller).
     * */
    @RequestMapping(value = "/feedbacks", method = RequestMethod.GET)
    public ModelAndView feedbacks(@RequestParam String login) {
        return pageModelAndView(login, "/feedbacks");
    }
    /**
     * requires unique seller login (what seller added lot).
     * */
    @RequestMapping(value = "/addLot", method = RequestMethod.GET)
    public ModelAndView addLot(@RequestParam String login) {
        return pageModelAndView(login,"/addLot");
    }
    /**
     * requires unique lot id (to what lot was added feedback).
     * */
    @RequestMapping(value = "/addFeedback", method = RequestMethod.GET)
    public ModelAndView addFeedback(@RequestParam String lotId) {
        return pageModelAndView(Integer.parseInt(lotId), "/addFeedback");
    }
    /**
     * requires unique lot id (that auction show).
     * */
    @RequestMapping(value = "/auction", method = RequestMethod.GET)
    public ModelAndView auction(@RequestParam String lotId) {
        return pageModelAndView(Integer.parseInt(lotId), "/auction");
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
    public ModelAndView signin_post(User user, ModelMap model) {
        System.out.println(user.getType());
        ModelAndView mav = new ModelAndView();
        try {
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
                user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
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
//            model.addAttribute("user", user);
            return mav;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("NON authorized or incorrect mail");
            mav.setViewName("/authorization");
            return mav;
        }
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search_post(@ModelAttribute("search") Search search, Model model) {
        System.out.println(search.getTitle());
        System.out.println(search.getStartPrice());
        System.out.println(search.getDuration());
        System.out.println(search.getStartTime());
        System.out.println(search.getDescription());

        return "redirect:/main";
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