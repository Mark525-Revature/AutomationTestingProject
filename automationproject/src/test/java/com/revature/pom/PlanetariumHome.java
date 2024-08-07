package com.revature.pom;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PlanetariumHome {
    private WebDriver driver;

    private String url = "http://localhost:8080";
    private String viewingPage = "http://localhost:8080/planetarium";

    @FindBy(id = "locationSelect")
    private WebElement locationSelect;

    @FindBy(id = "deleteInput")
    private WebElement deleteInput;

    @FindBy(id = "deleteButton")
    private WebElement deleteButton;

    @FindBy(id = "planetNameInput")
    private WebElement planetNameInput;

    @FindBy(xpath = "/html/body/div[1]/div[2]/input[2]")
    private WebElement planetImageInput;

    @FindBy(id = "usernameInput")
    private WebElement usernameInput;

    @FindBy(id = "passwordInput")
    private WebElement passwordInput;

    @FindBy(xpath = "/html/body/div[1]/div[2]/button")
    private WebElement planetSubmitButton;

    @FindBy(xpath = "/html/body/div/form/input[3]")
    private WebElement loginButton;

    @FindBy(xpath = "//select[@id='locationSelect']")
    private WebElement dropdown;

    @FindBy(xpath = "//tr")
    private List<WebElement> tableData;
    

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

    public String getDeleteInput(){
        return deleteInput.getAttribute("value");
    }

    // Planet Registraton

    public void goToViewingPage() { driver.get(viewingPage); }


    public void login(){
        driver.get(url);
        usernameInput.sendKeys("Batman");
        passwordInput.sendKeys("I am the night");
        loginButton.click();
    }

    public void enterCelestialBodyName(String name){
        deleteInput.sendKeys(name);
    }
    
    public void deleteCelestialBody(){
        deleteButton.click();
    }

    public void switchDropdownToPlanet(){
        Select dropdown = new Select(locationSelect);
        dropdown.selectByValue("planet");
    }

    public void enterPlanetName(String planetName){
        planetNameInput.sendKeys(planetName);
    }

    public void enterPlanetFile(String string){
        String filePath = "C:\\Users\\riley\\OneDrive\\Desktop\\GroupProj\\AutomationTestingProject\\automationproject\\src\\test\\resources\\Celestial-Images\\" + string;
        planetImageInput.sendKeys(filePath);
    }

    public void submitPlanet(){
        planetSubmitButton.click();
        handleAlert();
    }

    public int getTableLength() {
        return tableData.size();
    }

    // Moon

    public void selectMoonFromDropDown() {
        Select select = new Select(dropdown);
        select.selectByVisibleText("moon");
    }

    // Moon

    public void handleAlert() {
        try {
            WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(2));
            alertWait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (TimeoutException e){
            System.out.println("No alert present");
        }
    }

}
