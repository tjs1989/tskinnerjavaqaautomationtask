package stepDefinitions;

import apiCalls.AddItemCalls;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import tskinnerjavaqaautomationtask.ItemUtils;

import java.util.HashMap;
import java.util.Map;

import static setup.TestData.DEFAULT_ITEM_DATA_JSON;
import static setup.TestData.DEFAULT_ITEM_NAME;

public class AddItemStepDefs {

    AddItemCalls addItemCalls = new AddItemCalls();
    ItemUtils itemUtils = new ItemUtils();

    Response addItemResponse;
    Map<String, Object> itemDataJson = new HashMap<>();
    String itemName = "";

    String createdItemId = "";

    @Given("a {string} item is created")
    public void aItemIsCreated(String item) {
        itemName = item;
    }

    @And("the CPU model is {string}")
    public void theCPUModelIs(String itemCpuModel) {
        itemDataJson.put("CPU Model", itemCpuModel);
    }

    @And("the item has a price of {double}")
    public void theItemHasAPriceOf(double itemPrice) {
        itemDataJson.put("price", itemPrice);
    }

    @And("the item was made in the year {int}")
    public void theItemWasMadeInTheYear(int year) {
        itemDataJson.put("year", year);
    }

    @And("the item has a hdd capacity of {string}")
    public void theItemHasAHddCapacityOf(String hddSize) {
        itemDataJson.put("Hard Disk Size", hddSize);
    }

    @When("the request to add the item is made")
    public void theRequestToAddTheItemIsMade() {
        addItemResponse = addItemCalls.addItem(itemUtils.buildItemJson(itemName, itemDataJson));
    }

    @Then("a {int} response code is returned")
    public void aResponseCodeIsReturned(int expectedStatusCode) {
        addItemCalls.confirmCorrectStatusCodeIsReceived(addItemResponse, expectedStatusCode);
    }

    @And("a {string} is created")
    public void aIsCreated(String expectedItemName) {
        addItemCalls.confirmItemHasBeenAdded(addItemResponse, expectedItemName, itemDataJson);
    }

    @Given("a {string} item is created with the default specs")
    public void aItemIsCreatedWithTheDefaultSpecs(String itemName) {
        itemDataJson = DEFAULT_ITEM_DATA_JSON;
         addItemResponse = addItemCalls.addItem(itemUtils.buildItemJson(itemName, itemDataJson));
    }

    @Then("the created item ID is returned")
    public void theCreatedItemIDIsReturned() {
        createdItemId = addItemCalls.getCreatedItemID(addItemResponse);
    }

    @And("the item can be retrieved by ID from the list by ID endpoint")
    public void theItemCanBeRetrievedByIDFromTheListByIDEndpoint() {
        System.out.println(createdItemId);
    }
}
