package com.revature.step;

import org.junit.Assert;
import com.revature.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RemoveMoonStep{

    @Given("I am on the Planet and Moon viewing page")
    public void i_am_on_landing_moon_removal(){
        TestRunner.planetariumHome.goToViewingPage();
    }

    @When("I change the selector to Moon")
    public void i_change_the_selector_to_Delete_Moon() {
        TestRunner.planetariumHome.selectMoonFromDropDown();
    }

    @When("I insert the moon name {string}")
    public void i_insert_the_delete_moon_name(String string) {
        TestRunner.planetariumHome.enterMoonName();
    }

    @When("I click the delete button")
    public void i_click_the_delete_moon() {
        TestRunner.planetariumHome.enterMoonName();
    }

    @Then("no moon should be removed from the planetarium")
    public void the_user_should_be_able_to_delete_moon() {
        TestRunner.planetariumHome.goToViewingPage;
        Assert.assertEquals("Deletion attempt failed: please try again" , TestRunner.planetariumHome.getAlertText());
    }

}