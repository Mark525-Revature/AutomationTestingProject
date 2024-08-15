package com.revature.step;

import org.junit.Assert;
import com.revature.TestRunner;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MoonRemovalSteps {

    @When("I insert the moon name {string}")
    public void i_insert_the_delete_moon_name(String celestialBodyName) {
        TestRunner.planetariumHome.enterCelestialBodyName(celestialBodyName);
    }

    @Then("the moon should be removed from the planetarium")
    public void the_user_should_be_able_to_delete_moons() throws InterruptedException {
        Assert.assertTrue(TestRunner.planetariumHome.getCelestialBodyInfo());
    }

    @Then("no moon should be removed from the planetarium")
    public void the_user_should_not_be_able_to_delete_moon() {
        Assert.assertEquals("Failed to delete moon with name ", TestRunner.planetariumHome.getAlertText());
    }

}