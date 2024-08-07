package com.revature;

import com.revature.pom.PlanetariumHome;
import com.revature.pom.PlanetariumLogin;
import com.revature.pom.PlanetariumRegistration;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "classpath:features",
    glue = "com.revature.step",
    plugin = {
        "pretty",
        "html:src/test/resources/reports/html-report.html",
        "json:src/test/resources/reports/json-report.json"
    }
)
public class TestRunner {
    
    public static WebDriver driver;

    public static WebDriverWait wait;

    public static PlanetariumHome planetariumHome;
    public static PlanetariumRegistration planetariumRegistration;
    public static PlanetariumLogin planetariumLogin;

    @BeforeClass
    public static void setup(){
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        planetariumHome = new PlanetariumHome(driver);
        planetariumRegistration = new PlanetariumRegistration(driver);
        planetariumLogin = new PlanetariumLogin(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }

    @AfterClass
    public static void teardown(){
        driver.quit();
    }
    
}
