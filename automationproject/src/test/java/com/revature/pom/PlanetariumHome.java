package com.revature.pom;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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
    
    @FindBy(xpath = "//select[@id='locationSelect']")
    private WebElement dropdown;

    @FindBy(id = "deleteInput")
    private WebElement deleteInput;

    //@FindBy(id = "deleteButton")
    @FindBy(xpath = "//div[@id='deleteContainer']//button[@id='deleteButton']")
    private WebElement deleteButton;

    @FindBy(id = "usernameInput")
    private WebElement usernameInput;
    
    @FindBy(id = "passwordInput")
    private WebElement passwordInput;
    
    @FindBy(xpath = "/html/body/div/form/input[3]")
    private WebElement loginButton;
    
    @FindBy(id = "planetNameInput")
    private WebElement planetNameInput;

    @FindBy(id = "planetImageInput")
    private WebElement planetImageInput;

    @FindBy(xpath = "//div[@id='inputContainer']/button")
    private WebElement planetSubmitButton;

    @FindBy(xpath = "//div[@id='inputContainer']/input[@id='moonNameInput']")
    private WebElement moonNameInput;

    @FindBy(xpath = "//div[@id='inputContainer']/input[@id='orbitedPlanetInput']")
    private WebElement planetIdInput;

    @FindBy(xpath = "//div[@id='inputContainer']/input[@id='moonImageInput']")
    private WebElement moonImageInput;

    @FindBy(xpath = "//div[@id='inputContainer']/button")
    private WebElement moonSubmitButton;

    @FindBy(xpath = "//tr")
    private List<WebElement> tableData;

    public PlanetariumHome(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(){
        driver.get(url);
        usernameInput.sendKeys("Batman");
        passwordInput.sendKeys("I am the night");
        loginButton.click();
    }
    
    public void goToViewingPage() { driver.get(viewingPage); }
    
    // Planet and Moon Deletion
    public void enterCelestialBodyName(String name){
        deleteInput.sendKeys(name);
    }
    
    public String getDeleteInput(){
        return deleteInput.getAttribute("value");
    }
    
    public void deleteCelestialBody(){
        deleteButton.click();
    }
    
    public void switchDropdownToPlanet(){
        Select dropdown = new Select(locationSelect);
        dropdown.selectByValue("planet");
    }
    
    public void selectMoonFromDropDown() {
        Select dropdown = new Select(locationSelect);
        dropdown.selectByValue("moon");
    }
    
    // Planet Creation
    public void enterPlanetName(String planetName){
        planetNameInput.sendKeys(planetName);
    }
    
    public void enterPlanetFile(String string){
        Path filePath = Paths.get("src", "test", "resources", "Celestial-Images", string);
        planetImageInput.sendKeys(filePath.toAbsolutePath().toString());
    }
    
    public void submitPlanet(){
        planetSubmitButton.click();
        handleAlert();
        waitForCelestialBodyToBeCreated();
    }
    
    // Moon Creation
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
    
    public void submitMoon()
    {
        moonSubmitButton.click();
        handleAlert();
        waitForMoonToBeCreated();
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

    public String getAlertText(){
        WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(6));
        alertWait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        return alertText;
    }

    public void waitForCelestialBodyToBeCreated(){
        // List<WebElement> planetTable = driver.findElements(By.xpath("//tr//td[contains(text(),'planet')]"));
        // int length = planetTable.size() + 1;
        // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr["+ length +"]")));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr//td[contains(text(),'" + planetNameInput.getText() + "')]")));
    }

    public void waitForMoonToBeCreated(){
        List<WebElement> planetTable = driver.findElements(By.xpath("//tr//td[contains(text(),'moon')]"));
        int length = planetTable.size() + 1;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr["+ length +"]")));
    }

    public Boolean getPlanetInfo() {
        try{
            Thread.sleep(2000);
            if(driver.findElements(By.xpath("//tr//td[contains(text(),'" + getDeleteInput() + "')]")).size() == 0){
                return true;
            }
            else{
                return false;
            }
        }
        catch(NoSuchElementException|InterruptedException e){
            return true;
        }
    }

}
