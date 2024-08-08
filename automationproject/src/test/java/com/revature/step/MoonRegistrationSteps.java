package com.revature.step;

import org.junit.Assert;
import com.revature.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MoonRegistrationSteps {
    private int initialTableLength;

    @Given("Given I am on the Planet and Moon viewing page")
    public void given_I_am_on_the_Planet_and_Moon_viewing_page() {
        TestRunner.planetariumHome.goToViewingPage();
    }
    @When("I change the selector to Moon")
    public void i_change_the_selector_to_Moon() {
        TestRunner.planetariumHome.selectMoonFromDropDown();
    }
    @When("I insert the moon name {string}")
    public void i_insert_the_moon_name(String string) {
        TestRunner.planetariumHome.enterMoonName(string);
    }
    @When("I insert the planet ID {string}")
    public void i_insert_the_planet_ID(String string) {
        TestRunner.planetariumHome.enterPlanetId(string);
    }
    @When("I click the submit moon button")
    public void i_click_the_submit_moon_button() {
        initialTableLength = TestRunner.planetariumHome.getTableLength();
        TestRunner.planetariumHome.submitMoon();
    }
    @When("I choose an image for the moon {string}")
    public void i_choose_an_image_for_the_moon(String string) {
        TestRunner.planetariumHome.enterMoonFile(string);
    }
    @When("I insert no moon name")
    public void i_insert_no_moon_name() {
        TestRunner.planetariumHome.enterMoonName("");
    }
    @When("I insert no planet id")
    public void i_insert_no_planet_id() {
        TestRunner.planetariumHome.enterPlanetId("");
    }
    @Then("the moon should not be added to the planetarium")
    public void the_moon_should_not_be_added_to_the_planetarium() {
        int newTableLength = TestRunner.planetariumHome.getTableLength();
        Assert.assertEquals(initialTableLength, newTableLength);
    }
    @Then("the moon should be added to the planetarium")
    public void the_moon_should_be_added_to_the_planetarium() {
        int newTableLength = TestRunner.planetariumHome.getTableLength();
        Assert.assertEquals(initialTableLength + 1, newTableLength);
    }
}
