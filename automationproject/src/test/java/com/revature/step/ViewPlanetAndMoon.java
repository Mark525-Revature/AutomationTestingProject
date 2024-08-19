package com.revature.step;

import org.junit.Assert;
import com.revature.TestRunner;
import io.cucumber.java.en.Then;

public class ViewPlanetAndMoon {

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