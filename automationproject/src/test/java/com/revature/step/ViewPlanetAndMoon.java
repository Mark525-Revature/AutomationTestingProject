package com.revature.step;

import org.junit.Assert;
import com.revature.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ViewPlanetAndMoon {
    @Given("I am on the landing page")
    public void i_am_on_the_landing_page_View(){
        TestRunner.planetariumHome.goToLandingPage();
    }

    @When("I provide the username <valid username>")
    public void i_pick_the_option_to_login_View(String username) {
        TestRunner.planetariumHome.enterUsername(username);
    }

    @When("I provide the password <valid password>")
    public void i_pick_the_option_to_login_Views(String password) {
        TestRunner.planetariumHome.enterPassword(password);
    }

    @Then("I should be sent to the page with planets and moons")
    public void result(){
        TestRunner.planetariumHome.clickLoginButton();
        Assert.assertEquals("Viewing attempt failed: please try again", TestRunner.planetariumHome.getAlertText());
    }


}