package stepDefinitions;

import apiCalls.AddItemCalls;
import apiCalls.DeleteItemCalls;
import apiCalls.ListItemCalls;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import tskinnerjavaqaautomationtask.ItemUtils;

import java.util.HashMap;
import java.util.Map;

import static setup.TestData.DEFAULT_ITEM_DATA_JSON;
import static setup.TestData.DEFAULT_ITEM_NAME;

public class StepDefinitions {

    AddItemCalls addItemCalls = new AddItemCalls();
    ItemUtils itemUtils = new ItemUtils();
    ListItemCalls listItemCalls = new ListItemCalls();
    DeleteItemCalls deleteItemCalls = new DeleteItemCalls();

    Map<String, Object> itemDataJson = new HashMap<>();
    String itemName = "";

    String createdItemId = "";

    private io.restassured.response.Response apiResponse;


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
        apiResponse = addItemCalls.addItem(itemUtils.buildItemJson(itemName, itemDataJson));
    }

    @Then("a {int} response code is returned")
    public void aResponseCodeIsReturned(int expectedStatusCode) {
        addItemCalls.confirmCorrectStatusCodeIsReceived(apiResponse, expectedStatusCode);
    }

    @And("a {string} is created")
    public void aIsCreated(String expectedItemName) {
        addItemCalls.confirmCorrectItemInfoIsReturned(apiResponse, expectedItemName, itemDataJson);
    }

    @Given("a {string} item is created with the default specs")
    public void aItemIsCreatedWithTheDefaultSpecs(String itemName) {
        itemDataJson = DEFAULT_ITEM_DATA_JSON;
        apiResponse = addItemCalls.addItem(itemUtils.buildItemJson(itemName, itemDataJson));
    }

    @Then("the created item ID is returned")
    public void theCreatedItemIDIsReturned() {
        createdItemId = addItemCalls.getCreatedItemID(apiResponse);
    }

    @Then("the item can be retrieved by ID from the list by ID endpoint")
    public void theItemCanBeRetrievedByIDFromTheListByIDEndpoint() {
        iCallTheListItemByIDEndpointWithTheIDOf(createdItemId);
        listItemCalls.confirmCorrectItemIdInResponse(apiResponse, createdItemId);
    }

    @Given("^An item has been added to the list$")
    public void objectAddedToList() {
        apiResponse = addItemCalls.addItem(itemUtils.buildItemJson(DEFAULT_ITEM_NAME, DEFAULT_ITEM_DATA_JSON));
        addItemCalls.confirmCorrectStatusCodeIsReceived(apiResponse, 200);
        addItemCalls.confirmCorrectItemInfoIsReturned(apiResponse, DEFAULT_ITEM_NAME, DEFAULT_ITEM_DATA_JSON);
    }

    @When("^A user lists all items")
    public void userListsAllObjects() {
        apiResponse = listItemCalls.getAllItems();
    }

    @Then("^The list of all items is returned$")
    public void isListOfAllObjectsReturned() {
        listItemCalls.confirmAllItemsAreReturned(apiResponse);
    }

    @Given("a call to the list item by ID endpoint with the ID of {string}")
    public void iCallTheListItemByIDEndpointWithTheIDOf(String itemId) {
        apiResponse = listItemCalls.getItemById(itemId);
    }

    @Then("an empty response is seen from the list item by ID endpoint")
    public void iSeeAnEmptyResponse() {
        listItemCalls.confirmEmptyResponseIsReceived(apiResponse);
    }

    @When("the created item is deleted")
    public void theCreatedItemCanBeDeleted() {
        apiResponse = deleteItemCalls.deleteItem(createdItemId);
    }

    @And("a successful delete message is received")
    public void aSuccessfulDeleteMessageIsReceived() {
        deleteItemCalls.confirmItemHasBeenDeleted(apiResponse, createdItemId);
    }

    @And("a call to to get the item by predefined Id is made")
    public void aCallToToGetTheItemByPredefinedIdIsMade() {
        iCallTheListItemByIDEndpointWithTheIDOf(createdItemId);
    }

    @Given("I have a randomly generated item id")
    public void iHaveARandomlyGeneratedItemId() {
        createdItemId = itemUtils.getRandomStringId();
    }

    @Then("a message saying that the item does not exist is received")
    public void aMessageSayingThatTheItemDoesNotExistIsReceived() {
        deleteItemCalls.confirmItemIsNotFound(apiResponse, createdItemId);
    }
}
