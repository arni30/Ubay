package world.ucode.controller;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.DefaultSessionAttributeStore;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import world.ucode.models.Lot;
import world.ucode.models.User;
import world.ucode.services.UserService;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Controller
@ControllerAdvice
public class ModelController {

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String uploadFileHandler(@RequestParam("file") MultipartFile file,
                                    RedirectAttributes redirectAttributes) {
        System.out.println("hello");

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
            return "/signin";
        }

        try {

            //Get the file and save it somewhere
            byte[]bytes = file.getBytes();
            String UPLOADED__FOLDER = "/resources/";
            Path path = Paths.get(UPLOADED__FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
            System.out.println("file done");
            return "/image";

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "/signin";
    }



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
//        model.addAttribute("form", new Usr());
//        model.addAttribute("fileForm", new lot());
        return "/signin";
    }

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public void signup_post(@ModelAttribute("form") Usr usr, ModelMap model) {
//        System.out.println(usr.getType());
        System.out.println(usr.getType());
        System.out.println(usr.getPassword());
        System.out.println(usr.getConfirmpassword());
        System.out.println(usr.getEmail());
        System.out.println(usr.getUsername());
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