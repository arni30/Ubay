package world.ucode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import world.ucode.models.Lot;
import world.ucode.models.User;
import world.ucode.services.LotService;
import world.ucode.services.UserService;
import world.ucode.utils.ImageHandler;
import world.ucode.utils.PageModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;

@Controller
public class AddLotController {
    final private ModelAndView mav = new ModelAndView();
    @Autowired
    private PageModelAndView pageModelAndView;
    @Autowired
    private UserService userService;
    @Autowired
    private LotService lotService;

    /**
     * requires unique seller login (what seller added lot).
     * */
    @RequestMapping(value = "/addLot", method = RequestMethod.GET)
    public ModelAndView addLot(HttpServletRequest request) {
        return pageModelAndView.pageModelAndView(request.getUserPrincipal().getName(), "/addLot");
    }
    @RequestMapping(value = "/addLot", method = RequestMethod.POST)
    public ModelAndView addLot(Lot lot, @RequestParam("photo") MultipartFile file, HttpServletRequest request) throws IOException {
        System.out.println("ADDLOT");
        User seller = userService.findUser(request.getUserPrincipal().getName());
        lot.setSeller(seller);
        lot.setImage(file.getBytes());  // Data truncation: Data too long for column 'image' at row 1
//        ImageHandler.savePicture(file);  // проверка
        Timestamp curTime = new Timestamp(System.currentTimeMillis());
        curTime.setTime(curTime.getTime() + (2 * 60 * 60 * 1000));
        lot.setStartTime(curTime);
        lot.setFinishTime(addDays(curTime, lot.getDuration()));
        lot.setActive(true);
        seller.addLot(lot);
//        lot.setSeller(seller);
        lotService.saveLot(lot);
        mav.setViewName("redirect:/profile");
        return mav;
    }
    public static Timestamp addDays(Timestamp date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);// w ww.  j ava  2  s  .co m
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return new Timestamp(cal.getTime().getTime());

    }
}
