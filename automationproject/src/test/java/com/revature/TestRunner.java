package com.revature;

import java.time.Duration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import org.openqa.selenium.WebDriver;
import com.revature.pom.PlanetariumHome;
import io.cucumber.junit.CucumberOptions;
import com.revature.pom.PlanetariumLogin;
import org.openqa.selenium.chrome.ChromeDriver;
import com.revature.pom.PlanetariumRegistration;
import org.openqa.selenium.support.ui.WebDriverWait;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "classpath:features",
    // features = {"classpath:features/Login.feature",
    //             "classpath:features/MoonRegistration.feature"},
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
        Setup.resetTestDatabase();
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
