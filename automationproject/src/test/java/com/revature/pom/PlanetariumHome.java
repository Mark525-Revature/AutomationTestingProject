package com.revature.pom;

import java.time.Duration;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

// Currenlt using this for registration page 

public class PlanetariumHome {
    private WebDriver driver;

    private String url = "http://localhost:8080";

    // Planet Registraton

    @FindBy(id = "locationSelect")
    private WebElement locationSelect;

    @FindBy(id = "planetNameInput")
    private WebElement planetNameInput;

    @FindBy(id = "planetImageInput")
    private WebElement planetImageInput;

    @FindBy(xpath = "/html/body/div[1]/div[2]/button")
    private WebElement planetSubmitButton;

    // Planet Registraton

    public PlanetariumHome(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getAlertText(){
        WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        alertWait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        return alertText;
    }

    // Planet Registraton

    public void switchDropdownToPlanet(){
        Select dropdown = new Select(locationSelect);
        dropdown.selectByValue("planet");
    }

    public void enterPlanetName(String planetName){
        planetNameInput.sendKeys(planetName);
    }

    public void enterPlanetFile(String filePath){
        planetImageInput.click();
        planetImageInput.sendKeys(filePath);
    }

    public void submitPlanet(){
        planetSubmitButton.click();
    }

    public String getPlanetOnPage(String planetName){
        WebElement planet = driver.findElement(By.xpath("//td[text() = " + planetName + "]"));
        return planet.getText();
    }

    // Planet Registraton


}
