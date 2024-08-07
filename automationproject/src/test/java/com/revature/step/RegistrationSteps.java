package com.revature.step;

import org.junit.Assert;
import com.revature.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegistrationSteps {
    
    @Given("I am on the landing page")
    public void i_am_on_the_landing_page() {
        TestRunner.planetariumRegistration.goToLandingPage();
    }

    @When("I pick the option to register")
    public void i_pick_the_option_to_register() {
        TestRunner.planetariumRegistration.goToRegistrationPage();
    }

    @When("I provide the username {string}")
    public void i_provide_the_username_Batman_and_Robin_unite_now(String usernameId) {
        TestRunner.planetariumRegistration.enterUsername(usernameId);
    }

    @When("I provide no username")
    public void i_provide_no_username() {
        TestRunner.planetariumRegistration.enterUsername("");
    }

    @When("I provide the password {string}")
    public void i_provide_the_password_Riddler_and_Joker_disagree(String passwordId) {
        TestRunner.planetariumRegistration.enterPassword(passwordId);
    }

    @When("I provide no password")
    public void i_provide_no_password() {
        TestRunner.planetariumRegistration.enterPassword("");
    }

    @Then("the user should be registered")
    public void the_user_should_be_registered() {
        String username = TestRunner.planetariumRegistration.getUsername();
        String password = TestRunner.planetariumRegistration.getPassword();
        TestRunner.planetariumRegistration.clickCreateButton();
        Assert.assertEquals("Account created successfully with username \"" + username + "\" and password \"" + password +"\"" , TestRunner.planetariumHome.getAlertText());
    }

    @Then("the user should be informed registration failed")
    public void the_user_should_be_informed_registration_failed() {
        String username = TestRunner.planetariumRegistration.getUsername();
        String password = TestRunner.planetariumRegistration.getPassword();
        TestRunner.planetariumRegistration.clickCreateButton();
        Assert.assertEquals("Account creation failed with username \"" + username + "\" and password \"" + password +"\"" , TestRunner.planetariumHome.getAlertText());
    }

}
