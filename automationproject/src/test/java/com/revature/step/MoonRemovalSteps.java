package com.revature.step;

import org.junit.Assert;
import com.revature.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MoonRemovalSteps {
    @Then("no moon should be removed from the planetarium")
    public void no_moon_should_be_removed_from_the_planetarium() {
        Assert.assertEquals("Failed to delete moon with name ", TestRunner.planetariumHome.getAlertText());
    }
}
