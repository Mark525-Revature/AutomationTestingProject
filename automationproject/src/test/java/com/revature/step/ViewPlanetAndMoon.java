package com.revature.step;

import org.junit.Assert;
import com.revature.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ViewPlanetAndMoon {
    // @Given("I am on the landing page.")
    // public void i_am_on_the_landing_page_View(){
    //     TestRunner.planetariumLogin.goToLandingPage();
    // }

    // @When("I provide the username <valid username>")
    // public void i_pick_the_option_to_login_View(String username) {
    //     TestRunner.planetariumLogin.enterUsername(username);
    // }

    // @When("I provide the password <valid password>")
    // public void i_pick_the_option_to_login_Views(String password) {
    //     TestRunner.planetariumLogin.enterPassword(password);
    // }

    @Then("I should be sent to the page with planets and moons")
    public void the_user_should_be_logged_in(){
        TestRunner.planetariumLogin.clickLoginButtonValid();
        Assert.assertEquals("Home", TestRunner.driver.getTitle());
    }

    @Then("I should be not sent to the page with planets and moons")
    public void the_user_should_not_be_logged_in() {
        TestRunner.planetariumLogin.clickLoginButtonInvalid();    
        Assert.assertEquals("login attempt failed: please try again", TestRunner.planetariumLogin.getAlertText());
    }


}