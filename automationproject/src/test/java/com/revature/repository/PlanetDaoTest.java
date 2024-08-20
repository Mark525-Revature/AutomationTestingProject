package com.revature.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.revature.Setup;
import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.repository.planet.*;


public class PlanetDaoTest {
    private Planet planet;
    private PlanetDao dao;
    @Before
    public void setUp() throws Exception {
        Setup.resetTestDatabase();
        planet = new Planet();
        dao = new PlanetDaoImp();
    }
    @After
    public void tearDown() throws Exception{

    }

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
        //planet = dao.readPlanet("Earth").get();
        //Assert.assertEquals("Earth", planet.getPlanetName());
        Assert.assertTrue(dao.readPlanet("Earth").isPresent());
    }

    @Test
    public void readPlanetStringNegativeTest(){
        Assert.assertFalse(dao.readPlanet("Vegeta").isPresent());
    }

    @Test
    public void readPlanetIntegerPositiveTest(){
        //planet = dao.readPlanet(1).get();
        //Assert.assertEquals("Earth", planet.getPlanetName());
        Assert.assertTrue(dao.readPlanet(1).isPresent());
    }

    @Test
    public void readPlanetIntegerNegativeTest(){
        //planet = dao.readPlanet(4).get();
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
    public void updatePlanetTest(){
        planet.setPlanetId(1);
        planet.setPlanetName("Earth Prime");
        planet.setOwnerId(1);
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
