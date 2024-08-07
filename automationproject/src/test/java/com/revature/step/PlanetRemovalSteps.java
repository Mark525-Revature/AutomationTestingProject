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
        TestRunner.planetariumHome.switchDropdownToPlanet();
    }

    @When("I insert the planet name {string}")
    public void i_insert_the_planet_name(String name){
        TestRunner.planetariumHome.enterCelestialBodyName(name);
    }

    @When("I insert no planet name")
    public void i_insert_no_planet_name(){
        TestRunner.planetariumHome.enterCelestialBodyName("");
    }

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
        Assert.assertEquals("Failed to delete planet with name", TestRunner.planetariumHome.getAlertText());
    }
}
