package com.revature.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mockito;
import com.revature.Setup;
import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.exceptions.PlanetFail;
import com.revature.planetarium.repository.planet.*;
import com.revature.planetarium.service.planet.*;

public class PlanetServiceTest {

    private Planet existingPlanet;// id of 1, Earth, 1,
    private Planet existingPlanet2;// id of 1, Earth, 1,
    private Planet uniqueNamePlanet;// id of 2
    private Planet singleCharNamePlanet;// id of 3
    private Planet nameToLongPlanet;// id of 4
    private Planet noNamePlanet;// id of 5
    private PlanetDao dao;
    private PlanetService<Object> service;
    @Before
    public void setUp() throws InterruptedException {
        Setup.resetTestDatabase();
        existingPlanet = new Planet();
        existingPlanet.setPlanetId(1);
        existingPlanet.setPlanetName("Earth");
        existingPlanet.setOwnerId(1);
        existingPlanet2 = new Planet();
        existingPlanet2.setPlanetId(1);
        existingPlanet2.setPlanetName("Earth");
        existingPlanet2.setOwnerId(1);
        uniqueNamePlanet = new Planet();
        uniqueNamePlanet.setPlanetId(2);
        uniqueNamePlanet.setPlanetName("Amphitrite Euphrosyne Virginia");
        uniqueNamePlanet.setOwnerId(1);
        singleCharNamePlanet = new Planet();
        singleCharNamePlanet.setPlanetId(3);
        singleCharNamePlanet.setPlanetName("A");
        singleCharNamePlanet.setOwnerId(2);
        nameToLongPlanet = new Planet();
        nameToLongPlanet.setPlanetId(4);
        nameToLongPlanet.setPlanetName("Amphitrite Euphrosyne Virginia!");
        nameToLongPlanet.setOwnerId(1);
        noNamePlanet = new Planet();
        noNamePlanet.setPlanetId(5);
        noNamePlanet.setPlanetName("");
        noNamePlanet.setOwnerId(1);
        dao = Mockito.mock(PlanetDaoImp.class);
        service = new PlanetServiceImp<>(dao);
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

     @Test
     public void selectAllPlanetsPositiveTest(){
        List<Planet> planetList = new ArrayList<>(Arrays.asList(existingPlanet));
        Mockito.when(dao.readAllPlanets()).thenReturn(planetList);
        Assert.assertEquals(planetList, service.selectAllPlanets());
        Mockito.verify(dao).readAllPlanets();
     }

     @Test
     public void selectPlanetByIDPositiveTest(){
        Mockito.when(dao.readPlanet(existingPlanet.getPlanetId())).thenReturn(Optional.of(existingPlanet));
        Assert.assertEquals(existingPlanet, service.selectPlanet(existingPlanet.getPlanetId()));
        Mockito.verify(dao).readPlanet(existingPlanet.getPlanetId());
     }

     @Test
     public void selectPlanetByIDNegativeTest(){
        Mockito.when(dao.readPlanet(uniqueNamePlanet.getPlanetId())).thenReturn(Optional.empty());
        PlanetFail e = Assert.assertThrows(PlanetFail.class, () ->{service.selectPlanet(uniqueNamePlanet.getPlanetId());});
        Assert.assertEquals("Planet not found", e.getMessage());
        Mockito.verify(dao).readPlanet(uniqueNamePlanet.getPlanetId());
     }

     @Test
     public void selectPlanetByNamePositiveTest(){
        Mockito.when(dao.readPlanet(existingPlanet.getPlanetName())).thenReturn(Optional.of(existingPlanet));
        Assert.assertEquals(existingPlanet, service.selectPlanet(existingPlanet.getPlanetName()));
        Mockito.verify(dao).readPlanet(existingPlanet.getPlanetName());
     }

     @Test
     public void selectPlanetByNameNegativeTest(){
        Mockito.when(dao.readPlanet(uniqueNamePlanet.getPlanetName())).thenReturn(Optional.empty());
        PlanetFail e = Assert.assertThrows(PlanetFail.class, () ->{service.selectPlanet(uniqueNamePlanet.getPlanetName());});
        Assert.assertEquals("Planet not found", e.getMessage());
        Mockito.verify(dao).readPlanet(uniqueNamePlanet.getPlanetName());
     }

     @Test
     public void selectPlanetNegativeInvalidIdentifierTest(){
        PlanetFail e = Assert.assertThrows(PlanetFail.class, () ->{service.selectPlanet(uniqueNamePlanet);});
        Assert.assertEquals("identifier must be an Integer or String", e.getMessage());
        Mockito.verify(dao, Mockito.never()).readPlanet(uniqueNamePlanet.getPlanetName());
     }

     @Test
     public void selectPlanetByOwnerPositiveTest(){
        List<Planet> planetList = new ArrayList<>(Arrays.asList(existingPlanet, uniqueNamePlanet, singleCharNamePlanet));
        Mockito.when(dao.readPlanetsByOwner(1)).thenReturn(planetList.subList(1,2));
        Assert.assertEquals(planetList.subList(1, 2), service.selectByOwner(1));
        Mockito.verify(dao).readPlanetsByOwner(1);
     }

     @Test
     public void createPlanetPositiveTest(){
        Mockito.when(dao.readPlanet(existingPlanet.getPlanetName())).thenReturn(Optional.empty());
        Mockito.when(dao.createPlanet(existingPlanet)).thenReturn(Optional.of(existingPlanet));
        Assert.assertSame(existingPlanet, service.createPlanet(existingPlanet));
        Mockito.verify(dao).readPlanet(existingPlanet.getPlanetName());
        Mockito.verify(dao).createPlanet(existingPlanet);
     }

     @Test
     public void createPlanetPositiveNameWithMinimumCharactersTest(){
        Mockito.when(dao.readPlanet(singleCharNamePlanet.getPlanetName())).thenReturn(Optional.empty());
        Mockito.when(dao.createPlanet(singleCharNamePlanet)).thenReturn(Optional.of(singleCharNamePlanet));
        Assert.assertSame(singleCharNamePlanet, service.createPlanet(singleCharNamePlanet));
        Mockito.verify(dao).readPlanet(singleCharNamePlanet.getPlanetName());
        Mockito.verify(dao).createPlanet(singleCharNamePlanet);
     }

     @Test
     public void createPlanetNameWithMaximumCharactersTest(){
        Mockito.when(dao.readPlanet(uniqueNamePlanet.getPlanetName())).thenReturn(Optional.empty());
        Mockito.when(dao.createPlanet(uniqueNamePlanet)).thenReturn(Optional.of(uniqueNamePlanet));
        Assert.assertSame(uniqueNamePlanet, service.createPlanet(uniqueNamePlanet));
        Mockito.verify(dao).readPlanet(uniqueNamePlanet.getPlanetName());
        Mockito.verify(dao).createPlanet(uniqueNamePlanet);
     }

     @Test
     public void createPlanetNegativeNoNameTest(){
        Mockito.when(dao.readPlanet(noNamePlanet.getPlanetName())).thenReturn(Optional.empty());
        PlanetFail e = Assert.assertThrows(PlanetFail.class, () ->{service.createPlanet(noNamePlanet);});
        Assert.assertEquals("Planet name must be between 1 and 30 characters", e.getMessage());
        Mockito.verify(dao, Mockito.never()).createPlanet(noNamePlanet);
     }

     @Test
     public void createPlanetNegativeNameWithTooManyCharactersTest(){
        Mockito.when(dao.readPlanet(nameToLongPlanet.getPlanetName())).thenReturn(Optional.empty());
        PlanetFail e = Assert.assertThrows(PlanetFail.class, () ->{service.createPlanet(nameToLongPlanet);});
        Assert.assertEquals("Planet name must be between 1 and 30 characters", e.getMessage());
        Mockito.verify(dao, Mockito.never()).createPlanet(nameToLongPlanet);
     }
     
     @Test
     public void createPlanetNegativeNonUniqueNameTest(){
        Mockito.when(dao.readPlanet(existingPlanet.getPlanetName())).thenReturn(Optional.of(existingPlanet));
        PlanetFail e = Assert.assertThrows(PlanetFail.class, () ->{service.createPlanet(existingPlanet);});
        Assert.assertEquals("Planet name must be unique", e.getMessage());
        Mockito.verify(dao, Mockito.never()).createPlanet(existingPlanet);
     }

     @Test
     public void createPlanetNegativeFailedToCreateTest(){
        PlanetFail e = Assert.assertThrows(PlanetFail.class, () ->{service.createPlanet(existingPlanet);});
        Assert.assertEquals("Planet creation failed, please try again", e.getMessage());
        Mockito.verify(dao).createPlanet(existingPlanet);
     }

     @Test
     public void updatePlanetPositiveTest(){
        Mockito.when(dao.readPlanet(existingPlanet.getPlanetId())).thenReturn(Optional.of(existingPlanet));
        Mockito.when(dao.updatePlanet(existingPlanet)).thenReturn(Optional.of(existingPlanet));
        Assert.assertEquals(existingPlanet, service.updatePlanet(existingPlanet));
        Mockito.verify(dao).readPlanet(existingPlanet.getPlanetId());
        Mockito.verify(dao).updatePlanet(existingPlanet);
     }
     
     @Test
     public void updatePlanetPositiveNameWithMinimumCharactersTest(){
        Mockito.when(dao.readPlanet(singleCharNamePlanet.getPlanetId())).thenReturn(Optional.of(singleCharNamePlanet));
        Mockito.when(dao.updatePlanet(singleCharNamePlanet)).thenReturn(Optional.of(singleCharNamePlanet));
        Assert.assertEquals(singleCharNamePlanet, service.updatePlanet(singleCharNamePlanet));
        Mockito.verify(dao).readPlanet(singleCharNamePlanet.getPlanetId());
        Mockito.verify(dao).updatePlanet(singleCharNamePlanet);
     }
     
     @Test
     public void updatePlanetPositiveNameWithMaximumCharactersTest(){
        Mockito.when(dao.readPlanet(uniqueNamePlanet.getPlanetId())).thenReturn(Optional.of(uniqueNamePlanet));
        Mockito.when(dao.updatePlanet(uniqueNamePlanet)).thenReturn(Optional.of(uniqueNamePlanet));
        Assert.assertEquals(uniqueNamePlanet, service.updatePlanet(uniqueNamePlanet));
        Mockito.verify(dao).readPlanet(uniqueNamePlanet.getPlanetId());
        Mockito.verify(dao).updatePlanet(uniqueNamePlanet);
     }

     @Test
     public void updatePlanetNegativeNoNameTest(){
        Mockito.when(dao.readPlanet(noNamePlanet.getPlanetId())).thenReturn(Optional.empty());
        PlanetFail e = Assert.assertThrows(PlanetFail.class, () ->{service.updatePlanet(noNamePlanet);});
        Assert.assertEquals("Planet not found, could not update", e.getMessage());
        Mockito.verify(dao, Mockito.never()).updatePlanet(noNamePlanet);
     }

     @Test
     public void updatePlanetNegativeNameWithTooManyCharactersTest(){
        Mockito.when(dao.readPlanet(nameToLongPlanet.getPlanetId())).thenReturn(Optional.of(nameToLongPlanet));
        PlanetFail e = Assert.assertThrows(PlanetFail.class, () ->{service.updatePlanet(nameToLongPlanet);});
        Assert.assertEquals("Planet name must be between 1 and 30 characters, could not update", e.getMessage());
        Mockito.verify(dao, Mockito.never()).updatePlanet(nameToLongPlanet);
     }
     
     @Test
     public void updatePlanetNegativeNonUniqueNameTest(){
        Mockito.when(dao.readPlanet(existingPlanet2.getPlanetId())).thenReturn(Optional.of(existingPlanet));
        PlanetFail e = Assert.assertThrows(PlanetFail.class, () ->{service.updatePlanet(existingPlanet2);});
        Assert.assertEquals("Planet name must be unique, could not update", e.getMessage());
        Mockito.verify(dao).readPlanet(existingPlanet2.getPlanetId());
        Mockito.verify(dao).updatePlanet(existingPlanet2);
     }

     @Test
     public void deletePlanetByIDPositveTest(){
        Mockito.when(dao.deletePlanet(existingPlanet.getPlanetId())).thenReturn(true);
        Assert.assertEquals("Planet deleted successfully", service.deletePlanet(existingPlanet.getPlanetId()));
        Mockito.verify(dao).deletePlanet(existingPlanet.getPlanetId());
     }

     @Test
     public void deletePlanetByNamePositveTest(){
        Mockito.when(dao.deletePlanet(existingPlanet.getPlanetName())).thenReturn(true);
        Assert.assertEquals("Planet deleted successfully", service.deletePlanet(existingPlanet.getPlanetName()));
        Mockito.verify(dao).deletePlanet(existingPlanet.getPlanetName());
     }

     @Test
     public void deletePlanetNegativeInvalidIdentifierTest(){
        PlanetFail e = Assert.assertThrows(PlanetFail.class, () ->{service.deletePlanet(existingPlanet);});
        Assert.assertEquals("identifier must be an Integer or String", e.getMessage());
        Mockito.verify(dao, Mockito.never()).deletePlanet(existingPlanet.getPlanetName());
     }

     @Test
     public void deletePlanetNegativeNotFoundTest(){
        PlanetFail e = Assert.assertThrows(PlanetFail.class, () ->{service.deletePlanet(uniqueNamePlanet.getPlanetName());});
        Assert.assertEquals("Planet delete failed, please try again", e.getMessage());
        Mockito.verify(dao).deletePlanet(uniqueNamePlanet.getPlanetName());
     }
}
