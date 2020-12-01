package world.ucode.utils;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import world.ucode.models.Lot;
import world.ucode.models.User;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.List;

public class CreateJSON {

    public JSONObject auctionJSON(User seller, Lot lot) {
        JSONObject json = new JSONObject();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp curTime = new Timestamp(System.currentTimeMillis());
        //потом уберу - проверка на активность аукциона (@натся)
        if (lot.getFinishTime().before(curTime))
            lot.setActive(false);

        json.put("active", lot.getActive());
        json.put("id", lot.getId());
        json.put("image", "");
        json.put("title", lot.getTitle());
        json.put("seller", seller.getLogin());
        json.put("rate", seller.getAvarageRate());
        json.put("price", lot.getStartPrice());
        json.put("priceStep", lot.getBidStep());
        json.put("description", lot.getDescription());
        json.put("startTime", formatter.format(lot.getStartTime()).replace(' ','T'));
        json.put("endTime", formatter.format(lot.getFinishTime()).replace(' ','T'));

        return json;
    }

    public JSONArray mainShowLotsJSON(List<Lot> lots) {

        JSONArray jsonArray = new JSONArray();
            for (Lot lot : lots) {
                //потом уберу - проверка на активность аукциона (@натся)
                Timestamp curTime = new Timestamp(System.currentTimeMillis());
                if (lot.getFinishTime().before(curTime))
                    lot.setActive(false);
                JSONObject json = new JSONObject();

                json.put("id", lot.getId());
                json.put("title", lot.getTitle());
                json.put("category", lot.getCategory());
                json.put("price", lot.getStartPrice());
                json.put("active", lot.getActive());
                json.put("description", lot.getDescription());
                json.put("image", Base64.getEncoder().encodeToString(lot.getImage()));

                jsonArray.add(json);
            }

        return jsonArray;
    }
}
