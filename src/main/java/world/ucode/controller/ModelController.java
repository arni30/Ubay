package world.ucode.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import world.ucode.models.User;
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
    public String signin(ModelMap model) {
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
    @RequestMapping(value = "/addLot", method = RequestMethod.GET)
    public String addLot(ModelMap model) {
        return "/addLot";
    }
    @Async
    public void sendMail(User user) throws UnknownHostException {
        try (GenericXmlApplicationContext context = new GenericXmlApplicationContext()) {
            context.load("classpath:applicationContext.xml");
            context.refresh();
            JavaMailSender mailSender = context.getBean("mailSender", JavaMailSender.class);
            SimpleMailMessage templateMessage = context.getBean("templateMessage", SimpleMailMessage.class);

            // Создаём потокобезопасную копию шаблона.
            SimpleMailMessage mailMessage = new SimpleMailMessage(templateMessage);

            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("Registration confirmation");
            mailMessage.setText("To confirm your account, please click here : "
                    + "http://"+ "192.168.0.106" + ":8080/ubay/confirmation/?token=" + user.getToken());
            try {
                mailSender.send(mailMessage);
                System.out.println("Mail sended");
            } catch (MailException mailException) {
                System.out.println("Mail send failed.");
                mailException.printStackTrace();
            }
        }
    }
//    @RequestMapping(value = "/authorization/signin", method = RequestMethod.POST)
//    public String signup_post(User user, ModelMap model) {
////        System.out.println(usr.getType());
//        System.out.println(user.getUserRole());
//        System.out.println(user.getPassword());
//        System.out.println(user.getEmail());
//        System.out.println(user.getLogin());
//        System.out.println("hallo");
//        model.addAttribute("user",user);
//        userService.saveUser(user);
//        return "/main";
//
//    }
//    @RequestMapping(value = "/authorization", method = RequestMethod.POST)
//    public String signin_post(User user, ModelMap model) {
//        System.out.println(user.getType());
//        if (user.getType().equals("signin")) {
//            ModelAndView mav = new ModelAndView();
//            System.out.println(user.getPassword());
//            System.out.println(user.getLogin());
//            System.out.println("hallo");
//            try {
//                User newUser = userService.validateUser(user);
//                System.out.println(newUser.getEmail());
////                JSONObject jsonObj = new JsonObject();
////                mav.addObject("userSettingsJSON", mapper.writeValueAsString(userSet));
//                return "/main";
//            } catch (Exception e) {
//                System.out.println("EXCEPTION VALIDATION");
//            }
//        }
//        else {
//            System.out.println(user.getUserRole());
//            System.out.println(user.getPassword());
//            System.out.println(user.getEmail());
//            System.out.println(user.getLogin());
//            System.out.println("hallo");
//            model.addAttribute("user",user);
//            userService.saveUser(user);
//            return "/main";
//        }
//        return "/authorization";
//    }
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
    public ModelAndView signin_post(User user, ModelMap model) throws UnknownHostException {
        System.out.println(user.getType());
        ModelAndView mav = new ModelAndView();
        if (user.getType().equals("signin")) {
            System.out.println(user.getPassword());
            System.out.println(user.getLogin());
            System.out.println("hallo");
            try {
                ObjectMapper mapper = new ObjectMapper();
                User newUser = userService.validateUser(user);
                String json = mapper.writeValueAsString(newUser);
//                System.out.println(newUser.getEmail());
//                JSONObject obj = new JSONObject();
//                obj.put("userRole", newUser.getUserRole());
//                obj.put("login", newUser.getLogin());
//                obj.put("email", newUser.getEmail());
//                obj.put("balance", newUser.getBalance());
                mav.addObject("user", json);
                mav.setViewName("/profile");

//                System.out.println(obj);
                return mav;
            } catch (Exception e) {
                System.out.println("EXCEPTION VALIDATION");
                mav.setViewName("/authorization");
                return mav;
            }
        }
        else {
            user.setToken(getJWTToken(user.getLogin()));
            sendMail(user);
            System.out.println(user.getUserRole());
            System.out.println(user.getPassword());
            System.out.println(user.getEmail());
            System.out.println(user.getLogin());
            System.out.println("hallo");
            model.addAttribute("user",user);
            userService.saveUser(user);
            mav.setViewName("/main");
            return mav;
        }
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
private String getJWTToken(String username) {
    String secretKey = "mySecretKey";
    List<GrantedAuthority> grantedAuthorities = AuthorityUtils
            .commaSeparatedStringToAuthorityList("ROLE_USER");

    return Jwts
            .builder()
            .setId("softtekJWT")
            .setSubject(username)
            .claim("authorities",
                    grantedAuthorities.stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.toList()))
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 600000))
            .signWith(SignatureAlgorithm.HS512,
                    secretKey.getBytes()).compact();
    }
}