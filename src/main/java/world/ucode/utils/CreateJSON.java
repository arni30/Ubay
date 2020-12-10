package world.ucode.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import world.ucode.models.Bid;
import world.ucode.models.Feedback;
import world.ucode.models.Lot;
import world.ucode.models.User;
import world.ucode.services.BidService;
import world.ucode.services.LotService;
import world.ucode.services.UserService;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.List;
import java.util.Set;

public class CreateJSON {
    @Autowired
    private BidService bidService;
    @Autowired
    private LotService lotService;
    @Autowired
    private UserService userService;

    public JSONObject sellerInfoJson(String login) {
        JSONObject json = new JSONObject();

        json.put("username", login);
        json.put("rate", userService.findUser(login).getAvarageRate());
        return  json;
    }

    public JSONObject winnerJSON(Bid lastBid, String description, boolean exist) {
        JSONObject json = new JSONObject();

        json.put("exist", exist);
        if (exist) {
            json.put("bidder", lastBid.getBidder().getLogin());
            json.put("feedback", description);
        }
        return json;
    }

    public static JSONObject addFeedbackJSON(Lot lot) {
        JSONObject json = new JSONObject();
        json.put("lotId", lot.getId());
        json.put("title", lot.getTitle());
        return json;
    }

    public JSONObject auctionJSON(User seller, Lot lot) {
        JSONObject json = new JSONObject();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp curTime = new Timestamp(System.currentTimeMillis());
        if (lot.getActive() && lot.getFinishTime().before(curTime)) {
            lot.setActive(false);
            lotService.updateLot(lot);
        }
        Bid lastBid = bidService.findLast(lot.getId());
        if (lastBid != null)
            json.put("lastBidPrice", lastBid.getPrice());
        json.put("active", lot.getActive());
        json.put("id", lot.getId());
        json.put("title", lot.getTitle());
        json.put("seller", seller.getLogin());
        json.put("rate", seller.getAvarageRate());
        json.put("startPrice", lot.getStartPrice());
        json.put("priceStep", lot.getBidStep());
        json.put("description", lot.getDescription());
        json.put("startTime", formatter.format(lot.getStartTime()).replace(' ','T'));
        json.put("endTime", formatter.format(lot.getFinishTime()).replace(' ','T'));
        json.put("image", Base64.getEncoder().encodeToString(lot.getImage()));
        return json;
    }

    public JSONArray mainShowLotsJSON(List<Lot> lots) {
        JSONArray jsonArray = new JSONArray();
            for (Lot lot : lots) {
                Timestamp curTime = new Timestamp(System.currentTimeMillis());
                if (lot.getActive() && lot.getFinishTime().before(curTime)) {
                    lot.setActive(false);
                    lotService.updateLot(lot);
                }
                Bid lastBid = bidService.findLast(lot.getId());
                JSONObject json = new JSONObject();
                json.put("id", lot.getId());
                json.put("title", lot.getTitle());
                json.put("category", lot.getCategory());
                json.put("startPrice", lot.getStartPrice());
                if (lastBid != null) {
                    json.put("lastBidPrice", lastBid.getPrice());
                    json.put("lastBidder", lastBid.getBidder().getLogin());
                }
                json.put("active", lot.getActive());
                json.put("description", lot.getDescription());
                json.put("image", Base64.getEncoder().encodeToString(lot.getImage()));
                jsonArray.add(json);
            }
        return jsonArray;
    }

    public JSONArray profileBidderShowLotsJSON(Set<Bid> bids) {
        JSONArray jsonArray = new JSONArray();
        for (Bid bid : bids) {
            Timestamp curTime = new Timestamp(System.currentTimeMillis());
            if (bid.getLot().getActive() && bid.getLot().getFinishTime().before(curTime)) {
                bid.getLot().setActive(false);
                lotService.updateLot(bid.getLot());
            }
            Bid lastBid = bidService.findLast(bid.getLot().getId());
            JSONObject json = new JSONObject();
            json.put("id", bid.getLot().getId());
            json.put("title", bid.getLot().getTitle());
            json.put("category", bid.getLot().getCategory());
            json.put("startPrice", bid.getLot().getStartPrice());
            json.put("lastBidPrice", lastBid.getPrice());
            json.put("lastBidder", lastBid.getBidder().getLogin());
            json.put("active", bid.getLot().getActive());
            json.put("description", bid.getLot().getDescription());
            json.put("image", Base64.getEncoder().encodeToString(bid.getLot().getImage()));
            json.put("bidderPrice", bid.getPrice());
            json.put("bidderPriceActive", bid.getActive());
            jsonArray.add(json);
        }
        return jsonArray;
    }

    public JSONArray feedbacksJSON(List<Feedback> fs) {
        JSONArray jsonArray = new JSONArray();
        for (Feedback f : fs) {
            JSONObject json = new JSONObject();
            json.put("title", f.getLot().getTitle());
            json.put("bidder", f.getBidder().getLogin());
            json.put("rate", f.getRate());
            json.put("feedback", f.getDescription());
            jsonArray.add(json);
        }
        return jsonArray;
    }
}
