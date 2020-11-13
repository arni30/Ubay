package world.ucode.controller;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import world.ucode.models.Lot;
import world.ucode.models.User;
import world.ucode.services.UserService;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@MultipartConfig
@Controller
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
    public String signin() {
        return "/signin";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String signinPost(HttpServletRequest req) {

        Map<String, String[]> parameterMap = req.getParameterMap();

//        System.out.println(
//            req.getParameter("type") + ' ' +
//            req.getParameter("username") + ' ' +
//            req.getParameter("password") + ' ' //+
////            req.getParameter("email") + ' ' +
////            req.getParameter("confirmpassword") + ';'
//        );

        return "/signin";
    }

//    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = { "multipart/form-data" })
//    public void upload(@RequestPart("user") @Valid User user,
//                       @RequestPart("file") @Valid @NotNull @NotBlank MultipartFile file) {
//        System.out.println(user);
//        System.out.println("Uploaded File: ");
//        System.out.println("Name : " + file.getName());
//        System.out.println("Type : " + file.getContentType());
//        System.out.println("Name : " + file.getOriginalFilename());
//        System.out.println("Size : " + file.getSize());
//    }

    
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