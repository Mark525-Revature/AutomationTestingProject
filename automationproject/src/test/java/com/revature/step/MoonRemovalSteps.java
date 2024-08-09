package com.revature.step;

import org.junit.Assert;
import com.revature.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MoonRemovalSteps {
    int moonCount;

    @Given("I am on the Planet and Moon viewing page for deleting things ")
    public void I_am_on_the_Planet_and_Moon_viewing_page(){
        TestRunner.planetariumHome.login();
    }

    @When("I change the selector to Moon ")
    public void i_change_the_selector_to_Delete_Moon() {
        TestRunner.planetariumHome.selectMoonFromDropDown();
    }

    @When("I insert the moon name {string} ")
    public void i_insert_the_delete_moon_name(String celestialBodyName) {
        TestRunner.planetariumHome.enterCelestialBodyName(celestialBodyName);
    }

    @When("I click the delete button")
    public void i_click_the_delete_moon() {
        moonCount = TestRunner.planetariumHome.getTableLength();
        TestRunner.planetariumHome.deleteCelestialBody();
    }

    @Then("the moon should be removed from the planetarium")
    public void the_user_should_be_able_to_delete_moons() {
        Assert.assertEquals(moonCount - 1, TestRunner.planetariumHome.getTableLength());
    }

    @Then("no moon should be removed from the planetarium")
    public void the_user_should_not_be_able_to_delete_moon() {
        Assert.assertEquals("Failed to delete moon with name ", TestRunner.planetariumHome.getAlertText());
    }
}