package apiCalls;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import tskinnerjavaqaautomationtask.ObjectUtils;

import static io.restassured.RestAssured.given;
import static setup.TestData.BASE_URL;

public class APICalls {
    ObjectUtils objectUtils = new ObjectUtils();

    public void addObject() {
        Response res = given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body(objectUtils.buildObjectJson())
                .when()
                .post("");

        String body = res.getBody().asString();
        System.out.println(body);
    }
}
