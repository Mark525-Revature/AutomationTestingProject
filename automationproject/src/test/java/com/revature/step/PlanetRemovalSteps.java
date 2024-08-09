package com.revature.step;

import org.junit.Assert;
import com.revature.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PlanetRemovalSteps {

    @When("I click the Delete button")
    public void i_click_the_delete_button(){
        TestRunner.planetariumHome.deleteCelestialBody();
    }

    @Then("the planet should be removed from the planetarium")
    public void the_planet_should_be_removed_from_the_planetarium(){
        Assert.assertEquals("", TestRunner.planetariumHome.getAlertText());
    }

    @Then("the planet should not be removed from the planetarium")
    public void the_planet_should_not_be_removed_from_the_planetarium(){
        Assert.assertEquals("Failed to delete planet with name ", TestRunner.planetariumHome.getAlertText());
    }
}
