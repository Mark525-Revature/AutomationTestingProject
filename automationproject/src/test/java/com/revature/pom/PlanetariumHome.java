package com.revature.pom;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
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

    @FindBy(xpath = "/html/body/div[1]/div[2]/input[1]")
    private WebElement moonNameInput;

    @FindBy(xpath = "/html/body/div[1]/div[2]/input[2]")
    private WebElement planetIdInput;

    @FindBy(xpath = "/html/body/div[1]/div[2]/button")
    private WebElement moonSubmitButton;

    @FindBy(xpath = "/html/body/div[1]/div[2]/input[3]")
    private WebElement moonImageInput;

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
        // String filePath = "C:\\Users\\hwake\\Revature\\New Project\\automationproject\\AutomationTestingProject\\automationproject\\src\\test\\resources\\Celestial-Images\\" + string;
        // planetImageInput.sendKeys(filePath);
        Path filePath = Paths.get("src", "test", "resources", "Celestial-Images", string);
        planetImageInput.sendKeys(filePath.toAbsolutePath().toString());
    }

    public void submitPlanet(){
        planetSubmitButton.click();
        handleAlert();
        waitForCelestialBodyToBeCreated();
    }

    public int getTableLength() {
        return tableData.size();
    }

    // Moon

    public void selectMoonFromDropDown() {
        Select select = new Select(dropdown);
        select.selectByIndex(1);
        select.selectByIndex(0);
    }
    public void enterMoonName(String moonName){
        moonNameInput.sendKeys(moonName);
    }

    public void enterPlanetId(String planetId){
        planetIdInput.sendKeys(planetId);
    }

    public void submitMoon()
    {
        moonSubmitButton.click();
        handleAlert();
    }

    public void enterMoonFile(String string){
        // String filePath = "C:\\Users\\hwake\\Revature\\New Project\\automationproject\\AutomationTestingProject\\automationproject\\src\\test\\resources\\Celestial-Images\\" + string;
        // moonImageInput.sendKeys(filePath);
        Path filePath = Paths.get("src", "test", "resources", "Celestial-Images", string);
        planetImageInput.sendKeys(filePath.toAbsolutePath().toString());
    }

    // Moon

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

    public void waitForCelestialBodyToBeCreated(){
        List<WebElement> planetTable = driver.findElements(By.xpath("//tr//td[contains(text(),'planet')]"));
        int length = planetTable.size() + 1;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr["+ length +"]")));
    }

}
