package com.revature.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PlanetariumHome {
    private WebDriver driver;

    public PlanetariumHome(WebDriver driver){
        this.driver = driver;
    }
}
