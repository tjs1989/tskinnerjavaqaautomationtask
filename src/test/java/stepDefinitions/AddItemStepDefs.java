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

public class AddItemStepDefs {

    AddItemCalls addItemCalls = new AddItemCalls();
    ItemUtils itemUtils = new ItemUtils();

    Response addItemResponse;
    Map<String, Object> itemDataJson = new HashMap<>();
    String itemName = "";

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
}
