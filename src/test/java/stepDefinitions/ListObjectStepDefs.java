package stepDefinitions;

import apiCalls.AddItemCalls;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import tskinnerjavaqaautomationtask.ItemUtils;

import static setup.TestData.DEFAULT_ITEM_DATA_JSON;
import static setup.TestData.DEFAULT_ITEM_NAME;

public class ListObjectStepDefs {
        AddItemCalls addItemCalls = new AddItemCalls();
        ItemUtils itemUtils = new ItemUtils();
        @Given("^An object has been added to the list$")
        public void objectAddedToList(){
               Response addItemResponse = addItemCalls.addItem(itemUtils.buildItemJson(DEFAULT_ITEM_NAME, DEFAULT_ITEM_DATA_JSON));
               addItemCalls.confirmItemHasBeenAdded(addItemResponse, DEFAULT_ITEM_NAME, DEFAULT_ITEM_DATA_JSON);
        }

        @When("^A user lists all objects$")
        public void userListsAllObjects(){
                System.out.println("all objects listed");
        }


        @Then("^The list of all objects is returned$")
        public void isListOfAllObjectsReturned(){
                System.out.println("list of all objects returned");
        }
}
