package com.revature.step;

import org.junit.Assert;
import com.revature.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PlanetRemovalSteps {
    @Given("I am on the Planet and Moon viewing page")
    public void I_am_on_the_Planet_and_Moon_viewing_page(){
        TestRunner.planetariumHome.goToViewingPage();
    }

    @When("I change the selector to Planet")
    public void i_change_the_selector_to_Planet(){

    }

    @When("I insert the planet name {string}")
    public void i_insert_the_planet_name(String name){
        
    }

    @When("I insert no planet name")
    public void i_insert_no_planet_name(){

    }

    @When("I click the Delete button")
    public void i_click_the_delete_button(){
        
    }

    @Then("the planet should be removed from the planetarium")
    public void the_planet_should_be_removed_from_the_planetarium(){

    }

    @Then("the planet should not be removed from the planetarium")
    public void the_planet_should_not_be_removed_from_the_planetarium(){
        
    }
}
