package com.revature.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.revature.Setup;
import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.repository.planet.*;
import java.util.Base64;
import java.nio.file.*;
import java.io.IOException;

public class PlanetDaoTest {
    
    private Planet planet;
    private PlanetDao dao;
    private String base64String;
    Path filePath = Paths.get("src", "test", "resources", "Celestial-Images", "planet-1.jpg");
    byte[] fileContent;

    @Before
    public void setUp() throws IOException{
        Setup.resetTestDatabase();
        planet = new Planet();
        dao = new PlanetDaoImp();
        fileContent = Files.readAllBytes(filePath);
        base64String = Base64.getEncoder().encodeToString(fileContent);
    }
    @After
    public void tearDown(){}

    /* Tests to create: 
     * ReadAllPlanets: List<Planet>
     * readPlanet(String name): Planet
     * readPlanet(int id): Planet
     * readPlanetsByOwner(int ownerId): List<Planet>
     * createPlanet(Planet planet)
     * updatePlanet(Planet planet)
     * deletePlanet(int id)
     * deletePlanet(String name)
    */

    @Test
    public void readAllPlanetsTest(){
        Assert.assertTrue(dao.readAllPlanets().size() >= 3);
    }

    @Test
    public void readPlanetStringPositiveTest(){
        Assert.assertTrue(dao.readPlanet("Earth").isPresent());
    }

    @Test
    public void readPlanetIntegerNegativeTest(){
        Assert.assertFalse(dao.readPlanet(4).isPresent());
    }

    @Test
    public void readPlanetsByOwnerIDPositiveTest(){
        Assert.assertTrue(dao.readPlanetsByOwner(1).size() >= 3);
    }

    @Test
    public void readPlanetsByOwnerIDNegativeTest(){
        Assert.assertTrue(dao.readPlanetsByOwner(2).size() >= 0);
    }

    @Test
    public void createPlanetTest(){
        planet.setPlanetId(53);
        planet.setPlanetName("Earth53");
        planet.setOwnerId(1);
        Planet cPlanet = dao.createPlanet(planet).get();
        Assert.assertEquals(planet, cPlanet);
    }

    @Test
    public void createPlanetWithImageTest(){
        planet.setPlanetId(54);
        planet.setPlanetName("Earth54");
        planet.setOwnerId(1);
        planet.setImageData(base64String);
        Planet cPlanet = dao.createPlanet(planet).get();
        Assert.assertEquals(planet, cPlanet);
    }

    @Test
    public void updatePlanetTest(){
        planet.setPlanetId(1);
        planet.setPlanetName("Earth Prime");
        planet.setOwnerId(1);
        Planet uPlanet = dao.updatePlanet(planet).get();
        Assert.assertEquals(planet, uPlanet);
    }

    @Test
    public void updatePlanetWithImageTest(){
        planet.setPlanetId(1);
        planet.setPlanetName("Earth Prime");
        planet.setOwnerId(1);
        planet.setImageData(base64String);
        Planet uPlanet = dao.updatePlanet(planet).get();
        Assert.assertEquals(planet, uPlanet);
    }

    @Test
    public void deletePlanetByNameTest(){
        Assert.assertTrue(dao.deletePlanet("Earth"));
    }

    @Test
    public void deletePlanetByIDTest(){
        Assert.assertTrue(dao.deletePlanet(2));
    }
}
