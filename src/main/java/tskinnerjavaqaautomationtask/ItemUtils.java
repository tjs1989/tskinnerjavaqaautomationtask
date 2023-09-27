package tskinnerjavaqaautomationtask;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class ItemUtils {

    public Map<String, Object> buildItemDataJson(int year, double price, String cpuModel, String hddSize) {
        Map<String, Object> itemDataJson = new HashMap<>();
        itemDataJson.put("year", year);
        itemDataJson.put("price", price);
        itemDataJson.put("CPU Model", cpuModel);
        itemDataJson.put("Hard disk size", hddSize);

        return itemDataJson;
    }

    public String buildItemJson(String itemName, Map<String, Object> itemDataJson) {

        Map<String, Object> itemObjectJson = new HashMap<>();
        itemObjectJson.put("data", itemDataJson);
        itemObjectJson.put("name", itemName);

        Gson gson = new Gson();

        return gson.toJson(itemObjectJson);
    }

    public String getRandomStringId(){
        UUID randomUUID = UUID.randomUUID();
        return randomUUID.toString().replaceAll("-", "");
    }
}
