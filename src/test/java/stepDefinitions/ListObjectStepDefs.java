package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ListObjectStepDefs {
        @Given("^An object has been added to the list$")
        public void objectAddedToList(){
                System.out.println("add object to list");
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
