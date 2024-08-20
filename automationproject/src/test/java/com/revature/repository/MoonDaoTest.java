package com.revature.repository;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.Setup;
import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.repository.moon.MoonDao;
import com.revature.planetarium.repository.moon.MoonDaoImp;

import io.javalin.Javalin;

public class MoonDaoTest {
    
    // HttpClient webClient;
    // ObjectMapper objectMapper;
    // Javalin app;
    private MoonDao dao;
    private Moon positiveCreatedMoon;
    private Moon positiveReadMoon;

    @Before
    public void setUp() throws InterruptedException {
        Setup.resetTestDatabase();
        // webClient = HttpClient.newHttpClient();
        // objectMapper = new ObjectMapper();
        // app.start(8080);
        // Thread.sleep(1000);
        dao = new MoonDaoImp();
        positiveCreatedMoon = new Moon(4,"waxing crescent gibbous Moon1!",1);
        positiveReadMoon = new Moon(2,"Titan",2);
    }

    @After
    public void tearDown(){
        // app.stop();
    }

    @Test
    public void createMoon() {
        Moon returnedMoon = dao.createMoon(positiveCreatedMoon).get();
        Assert.assertSame(positiveCreatedMoon,returnedMoon);
    }

    @Test
    public void readMoonByIdPositive() {
        Assert.assertTrue(dao.readMoon(positiveReadMoon.getMoonId()).isPresent());
    }

    @Test
    public void readMoonByIdNegative() {
        Assert.assertFalse(dao.readMoon(positiveCreatedMoon.getMoonId()).isPresent());
    }

    @Test
    public void readMoonByNamePositive() {
        Assert.assertTrue(dao.readMoon(positiveReadMoon.getMoonName()).isPresent());
    }

    @Test
    public void readMoonByNameNegative() {
        Assert.assertFalse(dao.readMoon(positiveCreatedMoon.getMoonName()).isPresent());
    }

    @Test
    public void readAllMoons(){
        Assert.assertTrue(dao.readAllMoons().size() >= 3);
    }

    @Test
    public void readAllMoonsByPlanetIdPositive(){
        Assert.assertTrue(dao.readMoonsByPlanet(positiveReadMoon.getOwnerId()).size() >= 2);
    }

    @Test
    public void readAllMoonsByPlanetIdNegative(){
        Assert.assertFalse(dao.readMoonsByPlanet(positiveCreatedMoon.getOwnerId()).size() >= 2);
    }

    @Test
    public void updateMoon(){
        Moon updatedMoon = dao.updateMoon(positiveReadMoon).get();
        Assert.assertEquals(positiveReadMoon, updatedMoon);
    }

    @Test
    public void deleteMoonById(){
        Assert.assertTrue(dao.deleteMoon(positiveReadMoon.getMoonId()));
    }

    @Test
    public void deleteMoonByName(){
        Assert.assertTrue(dao.deleteMoon(positiveReadMoon.getMoonName()));
    }

    @Test
    public void deleteMoonByIdNegative(){
        Assert.assertFalse(dao.deleteMoon(positiveCreatedMoon.getMoonId()));
    }

    @Test
    public void deleteMoonByNameNegative(){
        Assert.assertFalse(dao.deleteMoon(positiveCreatedMoon.getMoonName()));
    }

}
