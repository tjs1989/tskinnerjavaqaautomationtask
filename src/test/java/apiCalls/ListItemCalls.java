package apiCalls;

import io.restassured.RestAssured;
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

    public void confirmAllItemsAreReturned(Response listItemsResponse){
        JsonPath jsonPathEvaluator = listItemsResponse.jsonPath();

        List<String> allItems;
        allItems = jsonPathEvaluator.getList("");

        assertThat(listItemsResponse.statusCode(), Matchers.equalTo(200));
        assertThat(allItems.size(), Matchers.greaterThan(1));

    }
}
