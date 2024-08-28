package com.revature.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import org.junit.After;
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
    private String usernameRegistered;
    private String usernameNotRegistered;
    

    @Before
    public void setUp(){
        Setup.resetTestDatabase();
		dao = new UserDaoImp();
        newUser = new User(2, "Robin", "I am the night!!!");
        usernameRegistered = "Batman";
        usernameNotRegistered = "Robin";
    }
    
    @After
    public void tearDown(){}

    @Test
    public void createUser(){
        User result = dao.createUser(newUser).get();
        assertEquals(newUser, result);
    }

    @Test
    public void findUserByUsernamePositive(){
        assertTrue(dao.findUserByUsername(usernameRegistered).isPresent());
    }

    @Test
    public void findUserByUsernameNegative(){
        assertFalse(dao.findUserByUsername(usernameNotRegistered).isPresent());
    }
}
