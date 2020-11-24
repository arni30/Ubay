package world.ucode.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import world.ucode.models.Lot;
import world.ucode.models.User;

import java.util.List;

public class CreateJSON {

    public JSONObject auctionJSON(User seller, Lot lot) {
        JSONObject json = new JSONObject();

        json.put("status", "active");
        json.put("id", lot.getId());
        json.put("image", "");
        json.put("title", lot.getTitle());
        json.put("seller", seller.getLogin());
        json.put("rate", seller.getAvarageRate());
        json.put("price", lot.getStartPrice());
        json.put("priceStep", lot.getBidStep());
        json.put("description", lot.getDescription());
        json.put("startTime", "2020-11-18T10:18:00");
        json.put("endTime", "2020-11-30T10:18:00");

        return json;
    }

    public JSONArray mainShowLotsJSON(List<Lot> lots) {

        JSONArray jsonArray = new JSONArray();
            for (Lot lot : lots) {
                JSONObject json = new JSONObject();

                json.put("id", lot.getId());
                json.put("title", lot.getTitle());
                json.put("category", lot.getCategory());
                json.put("price", lot.getStartPrice());
                json.put("status", "active");   // active: 1,
                json.put("description", lot.getDescription());
                json.put("image", "resources/favicon.ico");

                jsonArray.add(json);
            }

        return jsonArray;
    }
}
