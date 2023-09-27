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

    public void confirmCorrectStatusCodeIsReceived(Response response, int expectedStatusCode) {
        assertThat(response.statusCode(), Matchers.equalTo(expectedStatusCode));
    }

    public String getCreatedItemID(Response addItemResponse) {
        JsonPath responseJsonPath = addItemResponse.jsonPath();
        return responseJsonPath.get("id");
    }

    public void confirmItemHasBeenAdded(Response addItemResponse, String itemName, Map<String, Object> addItemJsonRequest) {
        JsonPath addItemResponseJsonPath = addItemResponse.jsonPath();

        String name = addItemResponseJsonPath.get("name");
        String hddSize = addItemResponseJsonPath.getString("data.'Hard Disk Size'");
        String cpuModel = addItemResponseJsonPath.get("data.'CPU Model'");
        int year = addItemResponseJsonPath.get("data.year");

        assertThat(name, Matchers.equalToIgnoringCase(itemName));
        assertThat(hddSize, Matchers.equalToIgnoringCase((String) addItemJsonRequest.get("Hard Disk Size")));
        assertThat(cpuModel, Matchers.equalToIgnoringCase((String) addItemJsonRequest.get("CPU Model")));
        assertThat(year, Matchers.equalTo(addItemJsonRequest.get("year")));
    }
}
