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

    private io.restassured.response.Response listItemsResponse;

    @Given("^An item has been added to the list$")
        public void objectAddedToList(){
               Response addItemResponse = addItemCalls.addItem(itemUtils.buildItemJson(DEFAULT_ITEM_NAME, DEFAULT_ITEM_DATA_JSON));
               addItemCalls.confirmItemHasBeenAdded(addItemResponse, DEFAULT_ITEM_NAME, DEFAULT_ITEM_DATA_JSON);
        }

        @When("^A user lists all items")
        public void userListsAllObjects(){
               listItemsResponse = listItemCalls.getAllItems();
        }


        @Then("^The list of all items is returned$")
        public void isListOfAllObjectsReturned(){
            listItemCalls.confirmAllItemsAreReturned(listItemsResponse);
        }
}
