package apiCalls;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static setup.TestData.BASE_URL;

public class AddItemCalls {
    public Response addItem(String requestBody) {
        return given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body(requestBody)
                .when()
                .post("");
    }

    public void confirmCorrectStatusCodeIsReceived(Response response, int expectedStatusCode){
        assertThat(response.statusCode(), Matchers.equalTo(expectedStatusCode));
    }

    public void confirmItemHasBeenAdded(Response addItemResponse, String itemName, Map<String, Object> addItemJsonRequest) {
        System.out.println(addItemResponse);

        JsonPath jsonPathEvaluator = addItemResponse.jsonPath();

        String name = jsonPathEvaluator.get("name");
        String hddSize = jsonPathEvaluator.getString("data.'Hard Disk Size'");
        System.out.println(hddSize);
        String cpuModel = jsonPathEvaluator.get("data.'CPU Model'");
        int year = jsonPathEvaluator.get("data.year");

        assertThat(name, Matchers.equalToIgnoringCase(itemName));
        assertThat(hddSize, Matchers.equalToIgnoringCase((String) addItemJsonRequest.get("Hard Disk Size")));
        assertThat(cpuModel, Matchers.equalToIgnoringCase((String) addItemJsonRequest.get("CPU Model")));
        assertThat(year, Matchers.equalTo(addItemJsonRequest.get("year")));
    }
}
