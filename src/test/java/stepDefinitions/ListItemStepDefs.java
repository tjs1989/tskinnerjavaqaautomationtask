package stepDefinitions;

import apiCalls.AddItemCalls;
import apiCalls.ListItemCalls;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import tskinnerjavaqaautomationtask.ItemUtils;

import static setup.TestData.DEFAULT_ITEM_DATA_JSON;
import static setup.TestData.DEFAULT_ITEM_NAME;


public class ListItemStepDefs {
    AddItemCalls addItemCalls = new AddItemCalls();
    ItemUtils itemUtils = new ItemUtils();
    ListItemCalls listItemCalls = new ListItemCalls();
    AddItemStepDefs addItemStepDefs = new AddItemStepDefs();

    private io.restassured.response.Response listItemsResponse;
    private io.restassured.response.Response listItemResponse;

    @Given("^An item has been added to the list$")
    public void objectAddedToList() {
        Response addItemResponse = addItemCalls.addItem(itemUtils.buildItemJson(DEFAULT_ITEM_NAME, DEFAULT_ITEM_DATA_JSON));
        addItemCalls.confirmCorrectStatusCodeIsReceived(addItemResponse, 200);
        addItemCalls.confirmItemHasBeenAdded(addItemResponse, DEFAULT_ITEM_NAME, DEFAULT_ITEM_DATA_JSON);
    }

    @When("^A user lists all items")
    public void userListsAllObjects() {
        listItemsResponse = listItemCalls.getAllItems();
    }


    @Then("^The list of all items is returned$")
    public void isListOfAllObjectsReturned() {
        listItemCalls.confirmAllItemsAreReturned(listItemsResponse);
    }

    @Given("a call to the list item by ID endpoint with the ID of {string}")
    public void iCallTheListItemByIDEndpointWithTheIDOf(String itemId) {
        listItemResponse = listItemCalls.getItemById(itemId);
    }

    @Then("an empty response is seen")
    public void iSeeAnEmptyResponse() {
        listItemCalls.confirmEmptyResponseIsReceived(listItemResponse);
    }
}
