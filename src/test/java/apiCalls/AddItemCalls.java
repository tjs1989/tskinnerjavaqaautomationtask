package apiCalls;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import static io.restassured.RestAssured.given;
import static setup.TestData.BASE_URL;

public class AddItemCalls {
    public Response addItem(String requestBody) {
        Response res = given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body(requestBody)
                .when()
                .post("");

        String body = res.getBody().asString();
        System.out.println(body);
        return res;
    }

    public void confirmItemHasBeenAdded(Response addItemResponse,String itemName, Map<String, Object> addItemJsonRequest){
        JsonPath jsonPathEvaluator = addItemResponse.jsonPath();

        String name = jsonPathEvaluator.get("name");
        String hddSize = jsonPathEvaluator.getString("data.'Hard Disk Size'");
        String cpuModel = jsonPathEvaluator.get("data.'CPU Model'");
        int year = jsonPathEvaluator.get("data.year");

        assertThat(addItemResponse.statusCode(), Matchers.equalTo(200));
        assertThat(name, Matchers.equalToIgnoringCase(itemName));
        assertThat(hddSize, Matchers.equalToIgnoringCase((String) addItemJsonRequest.get("Hard Disk Size")));
        assertThat(cpuModel, Matchers.equalToIgnoringCase((String) addItemJsonRequest.get("CPU Model")));
        assertThat(year, Matchers.equalTo(addItemJsonRequest.get("year")));
    }
}
