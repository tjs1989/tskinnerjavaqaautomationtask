package stepDefinitions;

import apiCalls.APICalls;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import tskinnerjavaqaautomationtask.ObjectUtils;

public class ListObjectStepDefs {
        APICalls apiCalls = new APICalls();
        @Given("^An object has been added to the list$")
        public void objectAddedToList(){
                apiCalls.addObject();
        }

        private void addDefaultItemToList() {
                System.out.println("hi");
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
