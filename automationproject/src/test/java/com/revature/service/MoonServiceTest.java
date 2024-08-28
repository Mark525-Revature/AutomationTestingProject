package com.revature.service;

import static org.mockito.Mockito.never;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import com.revature.Setup;
import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.exceptions.MoonFail;
import com.revature.planetarium.repository.moon.MoonDao;
import com.revature.planetarium.service.moon.MoonService;
import com.revature.planetarium.service.moon.MoonServiceImp;

public class MoonServiceTest {

    private MoonDao moonDao;
    private MoonService<Object> moonService;
    private Moon positiveCreatedMoon;
    private Moon positiveCreatedMoonShortName;
    private Moon negativeMoonNameTooShort;
    private Moon negativeMoonNameTooLong;
    private Moon existingMoon;
    Path filePath = Paths.get("src", "test", "resources", "Celestial-Images", "moon-1.jpg");
    private String base64String;
    byte[] fileContent;

    @Before
    public void setUp() throws IOException {
        Setup.resetTestDatabase();
        moonDao = Mockito.mock(MoonDao.class);
        moonService = new MoonServiceImp<>(moonDao);
        positiveCreatedMoon = new Moon(4,"waxing crescent gibbous Moon1!",1);
        positiveCreatedMoonShortName = new Moon(4,"M",1);
        negativeMoonNameTooShort = new Moon(5,"",1);
        negativeMoonNameTooLong = new Moon(5,"waxing crescent gibbous Moon!!!",1);
        existingMoon = new Moon(2,"Titan",2);
        fileContent = Files.readAllBytes(filePath);
        base64String = Base64.getEncoder().encodeToString(fileContent);
    }
    @After
    public void tearDown(){}

    @Test
    public void createMoonPositive(){
        Mockito.when(moonDao.readMoon(positiveCreatedMoon.getMoonName())).thenReturn(Optional.empty());
        Mockito.when(moonDao.createMoon(positiveCreatedMoon)).thenReturn(Optional.of(positiveCreatedMoon));
        Assert.assertSame(positiveCreatedMoon,moonService.createMoon(positiveCreatedMoon));
        Mockito.verify(moonDao).readMoon(positiveCreatedMoon.getMoonName());
        Mockito.verify(moonDao).createMoon(positiveCreatedMoon);
    }

    @Test
    public void createMoonPositiveWithImageTest(){
        positiveCreatedMoon.setImageData(base64String);
        Mockito.when(moonDao.readMoon(positiveCreatedMoon.getMoonName())).thenReturn(Optional.empty());
        Mockito.when(moonDao.createMoon(positiveCreatedMoon)).thenReturn(Optional.of(positiveCreatedMoon));
        Assert.assertSame(positiveCreatedMoon, moonService.createMoon(positiveCreatedMoon));
        Mockito.verify(moonDao).readMoon(positiveCreatedMoon.getMoonName());
        Mockito.verify(moonDao).createMoon(positiveCreatedMoon);
    }

    @Test
    public void createMoonPositiveNameWithMinimumCharactersWithImageTest(){
        positiveCreatedMoonShortName.setImageData(base64String);
        Mockito.when(moonDao.readMoon(positiveCreatedMoonShortName.getMoonName())).thenReturn(Optional.empty());
        Mockito.when(moonDao.createMoon(positiveCreatedMoonShortName)).thenReturn(Optional.of(positiveCreatedMoonShortName));
        Assert.assertSame(positiveCreatedMoonShortName, moonService.createMoon(positiveCreatedMoonShortName));
        Mockito.verify(moonDao).readMoon(positiveCreatedMoonShortName.getMoonName());
        Mockito.verify(moonDao).createMoon(positiveCreatedMoonShortName);
    }

    @Test
    public void createMoonNegativeNoNameWithImageTest(){
        negativeMoonNameTooShort.setImageData(base64String);
        Mockito.when(moonDao.readMoon(negativeMoonNameTooShort.getMoonName())).thenReturn(Optional.empty());
        MoonFail e = Assert.assertThrows(MoonFail.class, () ->{moonService.createMoon(negativeMoonNameTooShort);});
        Assert.assertEquals("Moon name must be between 1 and 30 characters", e.getMessage());
        Mockito.verify(moonDao, Mockito.never()).createMoon(negativeMoonNameTooShort);
    }

    @Test
    public void createMoonNegativeNameTooLongWithImageTest(){
        negativeMoonNameTooLong.setImageData(base64String);
        Mockito.when(moonDao.readMoon(negativeMoonNameTooLong.getMoonName())).thenReturn(Optional.empty());
        MoonFail e = Assert.assertThrows(MoonFail.class, () ->{moonService.createMoon(negativeMoonNameTooLong);});
        Assert.assertEquals("Moon name must be between 1 and 30 characters", e.getMessage());
        Mockito.verify(moonDao, Mockito.never()).createMoon(negativeMoonNameTooLong);
    }

    @Test
    public void createMoonNegativeNameTakenWithImageTest(){
        existingMoon.setImageData(base64String);
        Mockito.when(moonDao.readMoon(existingMoon.getMoonName())).thenReturn(Optional.of(existingMoon));
        MoonFail e = Assert.assertThrows(MoonFail.class, () -> {
            moonService.createMoon(existingMoon);
        });
        Assert.assertEquals("Moon name must be unique", e.getMessage());
        Mockito.verify(moonDao, Mockito.never()).createMoon(existingMoon);
    }

    @Test
    public void createMoonNegativeNameTooShort(){
        Mockito.when(moonDao.createMoon(negativeMoonNameTooShort)).thenReturn(Optional.empty());
        MoonFail e = Assert.assertThrows(MoonFail.class, () -> {
            moonService.createMoon(negativeMoonNameTooShort);
        });
    
        Assert.assertEquals("Moon name must be between 1 and 30 characters", e.getMessage());
        Mockito.verify(moonDao, Mockito.never()).createMoon(negativeMoonNameTooShort);
    }

    @Test
    public void createMoonNegativeNameTooLong(){
        Mockito.when(moonDao.createMoon(negativeMoonNameTooLong)).thenReturn(Optional.empty());
        MoonFail e = Assert.assertThrows(MoonFail.class, () -> {
            moonService.createMoon(negativeMoonNameTooLong);
        });
    
        Assert.assertEquals("Moon name must be between 1 and 30 characters", e.getMessage());
        Mockito.verify(moonDao, Mockito.never()).createMoon(negativeMoonNameTooLong);
    }

    @Test 
    public void createMoonNegativeNameTaken(){
        Mockito.when(moonDao.readMoon(existingMoon.getMoonName())).thenReturn(Optional.of(existingMoon));
        MoonFail e = Assert.assertThrows(MoonFail.class, () -> {
            moonService.createMoon(existingMoon);
        });
        Assert.assertEquals("Moon name must be unique", e.getMessage());
        Mockito.verify(moonDao, Mockito.never()).createMoon(existingMoon);
    }

    @Test
    public void createMoonNegativeFailedToCreate(){
        MoonFail e = Assert.assertThrows(MoonFail.class, () ->{moonService.createMoon(existingMoon);});
        Assert.assertEquals("Could not create new moon", e.getMessage());
        Mockito.verify(moonDao).createMoon(existingMoon);
    }

    @Test
    public void createMoonNegativeFailedToCreateWithImageTest(){
        existingMoon.setImageData(base64String);
        MoonFail e = Assert.assertThrows(MoonFail.class, () ->{moonService.createMoon(existingMoon);});
        Assert.assertEquals("Could not create new moon", e.getMessage());
        Mockito.verify(moonDao).createMoon(existingMoon);
    }

    // misc moon creation failure method??

    @Test
    public void selectMoonByIdPositive(){
        Mockito.when(moonDao.readMoon(existingMoon.getMoonId())).thenReturn(Optional.of(existingMoon));
        Assert.assertEquals(existingMoon,moonService.selectMoon(existingMoon.getMoonId()));
        Mockito.verify(moonDao).readMoon(existingMoon.getMoonId());
    }

    @Test
    public void selectMoonByNamePositive(){
        Mockito.when(moonDao.readMoon(existingMoon.getMoonName())).thenReturn(Optional.of(existingMoon));
        Assert.assertEquals(existingMoon,moonService.selectMoon(existingMoon.getMoonName()));
        Mockito.verify(moonDao).readMoon(existingMoon.getMoonName());
    }

    @Test
    public void selectMoonByInvalidIdentifier(){
        MoonFail e = Assert.assertThrows(MoonFail.class, () -> {
            moonService.selectMoon(existingMoon);
        });
    
        Assert.assertEquals("Identifier must be an Integer or String", e.getMessage());
        Mockito.verify(moonDao, Mockito.never()).readMoon(existingMoon.getMoonName());
    }

    @Test 
    public void selectMoonNegativeNotFoundById(){
        Mockito.when(moonDao.readMoon(positiveCreatedMoon.getMoonId())).thenReturn(Optional.empty());
        MoonFail e = Assert.assertThrows(MoonFail.class, () -> {
            moonService.selectMoon(positiveCreatedMoon.getMoonId());
        });
    
        Assert.assertEquals("Moon not found", e.getMessage());
        Mockito.verify(moonDao).readMoon(positiveCreatedMoon.getMoonId());
    }

    @Test 
    public void selectMoonNegativeNotFoundByName(){
        Mockito.when(moonDao.readMoon(positiveCreatedMoon.getMoonName())).thenReturn(Optional.empty());
        MoonFail e = Assert.assertThrows(MoonFail.class, () -> {
            moonService.selectMoon(positiveCreatedMoon.getMoonName());
        });
    
        Assert.assertEquals("Moon not found", e.getMessage());
        Mockito.verify(moonDao).readMoon(positiveCreatedMoon.getMoonName());
    }

    @Test
    public void selectAllMoonsPositive(){
        List<Moon> moonList = new ArrayList<>(Arrays.asList(
                                        new Moon(1, "Luna", 1),
                                        existingMoon,
                                        new Moon(3, "waxing crescent gibbous Moon!!", 2)
                                    ));
        Mockito.when(moonDao.readAllMoons()).thenReturn(moonList);
        Assert.assertEquals(moonService.selectAllMoons(), moonList);
        Mockito.verify(moonDao).readAllMoons();
    }

    @Test
    public void selectMoonsByPlanetIdPositive(){
        List<Moon> moonList = new ArrayList<>(Arrays.asList(
                                        new Moon(1, "Luna", 1),
                                        existingMoon,
                                        new Moon(3, "waxing crescent gibbous Moon!!", 2)
                                    ));
        Mockito.when(moonDao.readMoonsByPlanet(existingMoon.getOwnerId())).thenReturn(moonList.subList(1, 3));
        Assert.assertEquals(moonList.subList(1, 3),moonService.selectByPlanet(existingMoon.getOwnerId()));
        Mockito.verify(moonDao).readMoonsByPlanet(existingMoon.getOwnerId());
    }

    @Test
    public void updateMoonPositive(){
        Mockito.when(moonDao.readMoon(existingMoon.getMoonId())).thenReturn(Optional.of(existingMoon));
        Mockito.when(moonDao.updateMoon(existingMoon)).thenReturn(Optional.of(existingMoon));
        Assert.assertEquals(existingMoon, moonService.updateMoon(existingMoon));
        Mockito.verify(moonDao).readMoon(existingMoon.getMoonId());
        Mockito.verify(moonDao).updateMoon(existingMoon);
    }

    @Test
    public void updateMoonNegativeNotFound(){
        Mockito.when(moonDao.readMoon(positiveCreatedMoon.getMoonId())).thenReturn(Optional.empty());
        MoonFail e = Assert.assertThrows(MoonFail.class, () -> {
            moonService.updateMoon(positiveCreatedMoon);
        });
    
        Assert.assertEquals("Moon not found, could not update", e.getMessage());
        Mockito.verify(moonDao).readMoon(positiveCreatedMoon.getMoonId());
        Mockito.verify(moonDao,Mockito.never()).updateMoon(positiveCreatedMoon);
    }

    @Test
    public void updateMoonNegativeDuplicate() {
        Moon otherMoon = new Moon(1, "Titan", 2); 

        Mockito.when(moonDao.readMoon(existingMoon.getMoonId())).thenReturn(Optional.of(otherMoon));
        Mockito.when(moonDao.readMoon(existingMoon.getMoonName())).thenReturn(Optional.of(otherMoon));

        MoonFail e = Assert.assertThrows(MoonFail.class, () -> {
            moonService.updateMoon(existingMoon);
        });

        Assert.assertEquals("Moon name must be unique, could not update", e.getMessage());
        Mockito.verify(moonDao, never()).updateMoon(existingMoon);
    }

    @Test
    public void deleteMoonPositiveById(){
        Mockito.when(moonDao.deleteMoon(existingMoon.getMoonId())).thenReturn(true);
        Assert.assertEquals("Moon deleted successfully",moonService.deleteMoon(existingMoon.getMoonId()));
        Mockito.verify(moonDao).deleteMoon(existingMoon.getMoonId());
    }

    @Test
    public void deleteMoonPositiveByName(){
        Mockito.when(moonDao.deleteMoon(existingMoon.getMoonName())).thenReturn(true);
        Assert.assertEquals("Moon deleted successfully",moonService.deleteMoon(existingMoon.getMoonName()));
        Mockito.verify(moonDao).deleteMoon(existingMoon.getMoonName());
    }

    @Test
    public void deleteMoonNegativeInvalidIdentifier(){
        MoonFail e = Assert.assertThrows(MoonFail.class, () -> {
            moonService.deleteMoon(existingMoon);
        });
    
        Assert.assertEquals("Identifier must be an Integer or String", e.getMessage());
        Mockito.verify(moonDao, Mockito.never()).deleteMoon(existingMoon.getMoonName());
    }

    @Test
    public void deleteMoonNegativeNotFound(){
        MoonFail e = Assert.assertThrows(MoonFail.class, () -> {
            moonService.deleteMoon(positiveCreatedMoon.getMoonName());
        });
    
        Assert.assertEquals("Moon delete failed, please try again", e.getMessage());
        Mockito.verify(moonDao).deleteMoon(positiveCreatedMoon.getMoonName());
    }
}
