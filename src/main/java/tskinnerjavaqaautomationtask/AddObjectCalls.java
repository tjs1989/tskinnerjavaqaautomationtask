package tskinnerjavaqaautomationtask;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class AddObjectCalls {
    public void buildItemJsonObject(){

    }
    public void jsonObject(){
        Map<String,Object> objectDataJson = new HashMap<>();
        objectDataJson.put("year", 2019);
        objectDataJson.put("price", 1849.99);
        objectDataJson.put("CPU Model", "Intel Core i9");
        objectDataJson.put("Hard disk size", "1 TB");

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("data", objectDataJson);
        dataMap.put("name", "Apple MacBook Pro 16");

//        Map<String, Object> wholeObjectMap = new HashMap<>();
//        dataMap.put("name", dataMap);

        Gson gson = new Gson();

        System.out.println(gson.toJson(dataMap));

        RestAssured.baseURI = "https://api.restful-api.dev/objects";

        Response res = given()
                .header("Content-type", "application/json")
                .body(gson.toJson(dataMap))
                .when()
                .post("");

        String body = res.getBody().asString();
        System.out.println(body);





//        JSONObject data = new JSONObject();

//        data.put("name", "Apple MacBook Pro 16");
//        data.put("profile_image", "test.png");
//        data.put("employee_age", "30");
//        data.put("employee_salary", "11111");

//        System.out.println(data.toJSONString());
    }
}
