package apiCalls;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static setup.TestData.BASE_URL;

public class DeleteItemCalls {

    public Response deleteItem(String itemId){
        return given()
                .baseUri(BASE_URL)
                .header("Content-type", "application/json")
                .body("")
                .when()
                .delete("/" + itemId);
    }

    public void confirmItemHasBeenDeleted(Response response, String itemId){
        JsonPath responseJsonPath = response.jsonPath();
        String message = responseJsonPath.get("message");
        assertThat(message, Matchers.equalTo("Object with id = " + itemId + " has been deleted."));
    }

    public void confirmItemIsNotFound(Response response, String itemId){
        JsonPath responseJsonPath = response.jsonPath();
        String message = responseJsonPath.get("error");
        assertThat(message, Matchers.equalTo("Object with id = " + itemId + " doesn't exist."));
    }
}
