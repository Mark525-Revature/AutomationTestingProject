package com.revature.pom;

import java.time.Duration;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

// Currenlt using this for registration page 

public class PlanetariumHome {
    private WebDriver driver;

    private String url = "http://localhost:8080";

    @FindBy(xpath = "/html/body/div/form/a")
    private WebElement registrationLink;

    @FindBy(id = "usernameInput")
    private WebElement usernameInput;

    @FindBy(id = "passwordInput")
    private WebElement passwordInput;

    @FindBy(xpath = "/html/body/div/form/input[3]")
    private WebElement createButton;

    @FindBy(xpath = "/html/body/div/form/input[3]")
    private WebElement loginButton;

    public PlanetariumHome(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void goToLandingPage(){
        driver.get(url);
    }

    public void goToRegistrationPage(){
        registrationLink.click();
    }

    public void enterUsername(String username){
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password){
        passwordInput.sendKeys(password);
    }

    public String getUsername(){
        return usernameInput.getAttribute("value");
    }

    public String getPassword(){
        return passwordInput.getAttribute("value");
    }

    public void clickCreateButton(){
        createButton.click();
    }

    public void clickLoginButton() { loginButton.click(); }

    public String getAlertText(){
        WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        alertWait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        return alertText;
    }

}
