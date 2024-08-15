package com.revature.pom;

import java.util.List;
import java.time.Duration;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.openqa.selenium.By;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PlanetariumHome {
    
    private WebDriver driver;

    private String url = "http://localhost:8080";

    @FindBy(id = "usernameInput")
    private WebElement usernameInput;
    
    @FindBy(id = "passwordInput")
    private WebElement passwordInput;
    
    @FindBy(xpath = "/html/body/div/form/input[3]")
    private WebElement loginButton;

    @FindBy(id = "locationSelect")
    private WebElement locationSelect;

    @FindBy(id = "planetNameInput")
    private WebElement planetNameInput;

    @FindBy(id = "planetImageInput")
    private WebElement planetImageInput;

    @FindBy(id = "moonNameInput")
    private WebElement moonNameInput;

    @FindBy(id = "orbitedPlanetInput")
    private WebElement planetIdInput;

    @FindBy(id = "moonImageInput")
    private WebElement moonImageInput;

    @FindBy(className = "submit-button")
    private WebElement submitButton;

    @FindBy(id = "deleteInput")
    private WebElement deleteInput;

    @FindBy(id = "deleteButton")
    private WebElement deleteButton;

    @FindBy(xpath = "//tr")
    private List<WebElement> tableData;

    public PlanetariumHome(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


/*
---------------------------------------------------------------------------------------------------------------------------------
*/

    // PLANET REGISTRATION

    public void enterPlanetName(String planetName){
        planetNameInput.sendKeys(planetName);
    }

    public void enterPlanetFile(String string){
        Path filePath = Paths.get("src", "test", "resources", "Celestial-Images", string);
        planetImageInput.sendKeys(filePath.toAbsolutePath().toString());
    }

    public void waitForPlanetToBeCreated(){
        List<WebElement> planetTable = driver.findElements(By.xpath("//tr//td[contains(text(),'planet')]"));
        int length = planetTable.size() + 1;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr["+ length +"]")));
    }

    public void submitPlanet(){
        submitButton.click();
        handleAlert();
        waitForPlanetToBeCreated();
    }

    // PLANET REGISTRATION

/*
---------------------------------------------------------------------------------------------------------------------------------
*/


/*
---------------------------------------------------------------------------------------------------------------------------------
*/

    // MOON REGISTRATION

    public void enterMoonName(String moonName){
        moonNameInput.sendKeys(moonName);
    }

    public void enterPlanetId(String planetId){
        planetIdInput.sendKeys(planetId);
    }

    public void enterMoonFile(String string){
        Path filePath = Paths.get("src", "test", "resources", "Celestial-Images", string);
        moonImageInput.sendKeys(filePath.toAbsolutePath().toString());
    }

    public void waitForMoonToBeCreated(){
        List<WebElement> planetTable = driver.findElements(By.xpath("//tr//td[contains(text(),'moon')]"));
        int length = planetTable.size() + 1;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr["+ length +"]")));
    }

    public void submitMoon(){
        submitButton.click();
        handleAlert();
        waitForMoonToBeCreated();
    }

    // MOON REGISTRATION

/*
---------------------------------------------------------------------------------------------------------------------------------
*/


/*
---------------------------------------------------------------------------------------------------------------------------------
*/

    // CELESTIAL BODY REMOVAL

    public void enterCelestialBodyName(String name){
        deleteInput.sendKeys(name);
    }
    
    public void deleteCelestialBody(){
        deleteButton.click();
    }

    public String getDeleteInput(){
        return deleteInput.getAttribute("value");
    }

    // CELESTIAL BODY REMOVAL

/*
---------------------------------------------------------------------------------------------------------------------------------
*/


/*
---------------------------------------------------------------------------------------------------------------------------------
*/

    // UTILITY

    public void login(){
        driver.get(url);
        usernameInput.sendKeys("Batman");
        passwordInput.sendKeys("I am the night");
        loginButton.click();
    }

    public String getAlertText(){
        WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(1));
        alertWait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        return alertText;
    }

    public void switchDropdownToPlanet(){
        Select dropdown = new Select(locationSelect);
        dropdown.selectByValue("planet");
    }

    public void selectMoonFromDropDown() {
        Select dropdown = new Select(locationSelect);
        dropdown.selectByValue("moon");
    }

    public int getTableLength() {
        tableData = driver.findElements(By.xpath("//tr"));
        return tableData.size();
    }

    public void handleAlert() {
        try {
            WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(1));
            alertWait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.accept();
        } catch (TimeoutException e){
            System.out.println("No alert present");
        }
    }

    public Boolean getCelestialBodyInfo() {
        try{
            if(driver.findElements(By.xpath("//tr//td[contains(text(),'" + getDeleteInput() + "')]")).size() == 0){
                return true;
            }
            else{
                return false;
            }
        }
        catch(NoSuchElementException e){
            return true;
        }
    }

    // UTILITY

/*
---------------------------------------------------------------------------------------------------------------------------------
*/


}
