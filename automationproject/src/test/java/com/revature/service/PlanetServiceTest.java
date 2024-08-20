package com.revature.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import com.revature.Setup;
import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.repository.planet.*;
import com.revature.planetarium.service.planet.*;

public class PlanetServiceTest {

    private Planet planet;
    private PlanetDao dao;
    private PlanetServiceImp service;
    @Before
    public void setUp() throws InterruptedException {
        Setup.resetTestDatabase();
        dao = Mockito.mock(PlanetDaoImp.class);
        service = new PlanetServiceImp(dao);
    }
    @After
    public void tearDown(){}

    /* Tests to create:
     * selectAllPlanets()
     * selectPlanet(int ID)
     * selectPlanet(String name)
     * selectPlanetByOwner(int ID)
     * createPlanet(Planet planet) name length enforced: 0, 1, 30, 31 && unique name enforced
     * updatePlanet(Planet planet) name length enforced: 0, 1, 30, 31 && unique name enforced
     * deletePlanet(int ID) returns a the string: "Planet deleted successfully"
     * deletePlanet(String name) returns a the string: "Planet deleted successfully"
     */
}
