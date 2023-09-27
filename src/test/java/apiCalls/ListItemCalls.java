package apiCalls;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static setup.TestData.BASE_URL;

public class ListItemCalls {

    public Response getAllItems() {
        return given().baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .when()
                .get();
    }

    public Response getItemById(String itemId) {
        return given().baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .param("id", itemId)
                .when()
                .get();
    }

    public void confirmEmptyResponseIsReceived(Response response) {
        assertThat(response.getBody().asString(), Matchers.equalTo("[]"));
    }

    public void confirmCorrectItemIdInResponse(Response response, String expectedId) {
        JsonPath responseJsonPath = response.jsonPath();

        List<Object> allItems;
        allItems = responseJsonPath.getList("");

        assertThat(allItems.size(), Matchers.equalTo(1));

        String itemString = allItems.get(0).toString();
        assertThat(itemString, Matchers.containsString("id=" + expectedId));
    }


    public void confirmAllItemsAreReturned(Response listItemsResponse) {
        JsonPath listItemsJsonPath = listItemsResponse.jsonPath();

        List<String> allItems;
        allItems = listItemsJsonPath.getList("");

        assertThat(allItems.size(), Matchers.greaterThan(1));
    }
}
