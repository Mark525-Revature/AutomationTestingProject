package com.revature.step;

import org.junit.Assert;
import com.revature.TestRunner;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;

public class PlanetRegistrationSteps {

    private int initialTableLength;

    @Given("I am on the Planet and Moon viewing page")
    public void i_am_on_the_Planet_and_Moon_viewing_page() {
        TestRunner.planetariumHome.login();
    }

    @When("I change the selector to Planet")
    public void i_change_the_selector_to_Planet() {
        TestRunner.planetariumHome.switchDropdownToPlanet();
    }

    @When("I insert the planet name {string}")
    public void i_insert_the_planet_name(String string) {
        TestRunner.planetariumHome.enterPlanetName(string);
    }

    @When("I click the Submit Planet button")
    public void i_click_the_Submit_Planet_button() {
        initialTableLength = TestRunner.planetariumHome.getTableLength();
        TestRunner.planetariumHome.submitPlanet();
    }

    @When("I click choose File")
    public void i_click_choose_File() {
        TestRunner.planetariumHome.enterPlanetFile("planet-1.jpg");
    }

    @When("I choose an image for the planet {string}")
    public void i_choose_an_image_for_the_planet(String string) {
    }

    @When("I choose an image for the planet planet-{int}.jpg")
    public void i_choose_an_image_for_the_planet_planet_jpg(Integer int1) {
    }

    @When("I insert no planet name")
    public void i_insert_no_planet_name() {
        TestRunner.planetariumHome.enterPlanetName("");
    }

    @Then("the planet should be added to the planetarium")
    public void the_planet_should_be_added_to_the_planetarium() {
        int newTableLength = TestRunner.planetariumHome.getTableLength();
        Assert.assertEquals(initialTableLength + 1, newTableLength);
    }

    @Then("the planet should not be added to the planetarium")
    public void the_planet_should_not_be_added_to_the_planetarium() {
        int newTableLength = TestRunner.planetariumHome.getTableLength();
        Assert.assertEquals(initialTableLength, newTableLength);
    }

}
