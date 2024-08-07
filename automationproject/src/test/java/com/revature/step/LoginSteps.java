package com.revature.step;
import org.junit.Assert;
import com.revature.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
    @When("I pick the option to login")
    public void i_pick_the_option_to_login() {
    }
    @Then("the user should be logged in with the planetarium")
    public void the_user_should_be_logged_in_with_the_planetarium() {
        TestRunner.planetariumLogin.clickLoginButton();
        Assert.assertEquals("Home", TestRunner.driver.getTitle());
    }
    @Then("the user should not be logged in with the planetarium")
    public void the_user_should_not_be_logged_in_with_the_planetarium() {
        TestRunner.planetariumLogin.clickLoginButton();
        Assert.assertEquals("login attempt failed: please try again" , TestRunner.planetariumHome.getAlertText());
    }
}
