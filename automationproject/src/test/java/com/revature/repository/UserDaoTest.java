package com.revature.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.revature.Setup;
import com.revature.planetarium.entities.User;
import com.revature.planetarium.exceptions.UserFail;
import com.revature.planetarium.repository.user.UserDao;
import com.revature.planetarium.repository.user.UserDaoImp;


public class UserDaoTest {

    private UserDao dao;
    private User newUser;
    private User nonUniqueUser;
    private User tooLongUsername;
    private User tooLongPassword;
    private String actualMessage;
    private String usernameRegistered;
    private String usernameNotRegistered;
    private String expectedMessage;
    private User tooLongUsernameAndPassword;
    

    @Before
    public void setUp() throws InterruptedException {
        Setup.resetTestDatabase();
		dao = new UserDaoImp();
        newUser = new User(2, "Robin", "I am the night!!!");
        nonUniqueUser = new User(2, "Batman", "I am the night!!!");
        tooLongUsername = new User(2, "Gordon and Clark are friends!!!", "I am the night!!!");
        tooLongPassword = new User(2, "Robin", "Reverse Flash strikes again!!!!");
        tooLongUsernameAndPassword = new User(2, "Gordon and Clark are friends!!!", "Reverse Flash strikes again!!!!");
        expectedMessage= "failed";
        usernameRegistered = "Batman";
        usernameNotRegistered = "Robin";
    }
    @Test
    public void createUser(){
        User result = dao.createUser(newUser).get();
        assertEquals(newUser, result);
    }
    @Test
    public void createUserNegativeNonUniqueUsername(){
        Exception exception = assertThrows(UserFail.class, () -> {
            dao.createUser(nonUniqueUser);
        });
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    public void createUserNegativeTooLongUsername(){
        Exception exception = assertThrows(UserFail.class, () -> {
            dao.createUser(tooLongUsername);
        });
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    public void createUserNegativeTooLongPassword(){
        Exception exception = assertThrows(UserFail.class, () -> {
            dao.createUser(tooLongPassword);
        });
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    public void createUserNegativeTooLongUsernameAndPassword(){
        Exception exception = assertThrows(UserFail.class, () -> {
            dao.createUser(tooLongUsernameAndPassword);
        });
        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void findUserByUsernamePositive(){
        assertTrue(dao.findUserByUsername(usernameRegistered).isPresent());
    }
    @Test
    public void findUserByUsernameNegative(){
        assertFalse(dao.findUserByUsername(usernameNotRegistered).isPresent());
    }

    @After
    public void tearDown(){
        //app.stop();
    }
}
