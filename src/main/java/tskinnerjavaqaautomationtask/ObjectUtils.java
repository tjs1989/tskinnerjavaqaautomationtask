package tskinnerjavaqaautomationtask;

import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;


public class ObjectUtils {

    public String buildObjectJson(){
        Map<String,Object> objectDataJson = new HashMap<>();
        objectDataJson.put("year", 2019);
        objectDataJson.put("price", 1849.99);
        objectDataJson.put("CPU Model", "Intel Core i9");
        objectDataJson.put("Hard disk size", "1 TB");

        Map<String, Object> itemObjectJson = new HashMap<>();
        itemObjectJson.put("data", objectDataJson);
        itemObjectJson.put("name", "Apple MacBook Pro 16");

        Gson gson = new Gson();

        return gson.toJson(itemObjectJson);
    }
}
