package world.ucode.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import world.ucode.models.Bid;
import world.ucode.models.Feedback;
import world.ucode.models.Lot;
import world.ucode.models.User;
import world.ucode.services.BidService;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.List;

public class CreateJSON {
    private BidService bidService = new BidService();

    public JSONObject winnerJSON(Bid lastBid, String description) {
        JSONObject json = new JSONObject();

        json.put("bidder", lastBid.getBidder().getLogin());
        json.put("feedback", description);
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

//        Timestamp startTime = lot.getStartTime();
//        startTime.setTime(startTime.getTime() - (2 * 60 * 60 * 1000));
//        Timestamp finishTime = lot.getFinishTime();
//        finishTime.setTime(finishTime.getTime() - (2 * 60 * 60 * 1000));

        Timestamp curTime = new Timestamp(System.currentTimeMillis());
        //потом уберу - проверка на активность аукциона (@натся)
        if (lot.getFinishTime().before(curTime))
            lot.setActive(false);

        Bid lastBid = bidService.findLast(lot.getId());
        if (lastBid != null) {
            json.put("lastBidPrice", lastBid.getPrice());
        }

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
                //потом уберу - проверка на активность аукциона (@натся)
                Timestamp curTime = new Timestamp(System.currentTimeMillis());
                if (lot.getFinishTime().before(curTime))
                    lot.setActive(false);
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

    public JSONArray feedbacksJSON(List<Feedback> fs) {
        JSONArray jsonArray = new JSONArray();
        for (Feedback f : fs) {
            JSONObject json = new JSONObject();

            json.put("title", f.getLot().getTitle());
//            json.put("bidder", );
            json.put("rate", f.getRate());
            json.put("feedback", f.getDescription());

            jsonArray.add(json);
        }
        return jsonArray;
    }
}
