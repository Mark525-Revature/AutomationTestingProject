package com.revature.service;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.revature.Setup;
import com.revature.planetarium.entities.User;
import com.revature.planetarium.exceptions.UserFail;
import com.revature.planetarium.repository.user.UserDao;
import com.revature.planetarium.service.user.UserService;
import com.revature.planetarium.service.user.UserServiceImp;


public class UserServiceTest {
    private UserDao userDao;
    private UserService userService;
    private User positiveCreatedUser;
    private User negativeUsernameTooLong;
    private User negativePasswordTooLong;
    private User userAlreadyRegistered;
    private User negativeAuthenticateUserPasswordIncorect;

    

    @Before
    public void setUp() throws InterruptedException {
        Setup.resetTestDatabase();
        userDao = Mockito.mock(UserDao.class);
        userService = new UserServiceImp((userDao));
        positiveCreatedUser = new User(2, "Robin", "I am the night!!!");
        negativeUsernameTooLong = new User(2, "Gordon and Clark are friends!!!", "I am the night!!!");
        negativePasswordTooLong = new User(2, "Robin", "Reverse Flash strikes again!!!!");
        userAlreadyRegistered = new User(2, "Batman", "I am the night!!!");
        negativeAuthenticateUserPasswordIncorect = new User(2, "Robin", "I am the day!!!");

    }
    @Test
    public void createUserPositive(){
        Mockito.when(userDao.createUser(positiveCreatedUser)).thenReturn(Optional.of(positiveCreatedUser));
        assertTrue(userService.createUser(positiveCreatedUser).contains("Created user with username Robin and password I am the night!!!"));
        Mockito.verify(userDao).createUser(positiveCreatedUser);
    }
    @Test
    public void createUserNegativeTooLongUsername(){
        Mockito.when(userDao.createUser(negativeUsernameTooLong)).thenReturn(Optional.empty());
        UserFail e = assertThrows(UserFail.class, () -> {
            userService.createUser(negativeUsernameTooLong);
        });
        Assert.assertEquals("Username must be between 1 and 30 characters", e.getMessage());
        Mockito.verify(userDao, Mockito.never()).createUser(negativeUsernameTooLong);
    }
    @Test
    public void createUserNegativeTooPassword(){
        Mockito.when(userDao.createUser(negativePasswordTooLong)).thenReturn(Optional.empty());
        UserFail e = assertThrows(UserFail.class, () -> {
            userService.createUser(negativePasswordTooLong);
        });
        Assert.assertEquals("Password must be between 1 and 30 characters", e.getMessage());
        Mockito.verify(userDao, Mockito.never()).createUser(negativePasswordTooLong);
    }
    @Test
    public void createUserNegativNonUniqueUsername(){
        Mockito.when(userDao.findUserByUsername(userAlreadyRegistered.getUsername())).thenReturn(Optional.of(userAlreadyRegistered));
        Mockito.when(userDao.createUser(userAlreadyRegistered)).thenReturn(Optional.empty());
        UserFail e = assertThrows(UserFail.class, () -> {
            userService.createUser(userAlreadyRegistered);
        });
        Assert.assertEquals("Username is already in use", e.getMessage());
        Mockito.verify(userDao, Mockito.never()).createUser(userAlreadyRegistered);
    }
    @Test
    public void authenticatePositive(){
        Mockito.when(userDao.findUserByUsername(userAlreadyRegistered.getUsername())).thenReturn(Optional.of(userAlreadyRegistered));
        assertSame(userAlreadyRegistered, userService.authenticate(userAlreadyRegistered));
        Mockito.verify(userDao).findUserByUsername(userAlreadyRegistered.getUsername());
    }
    @Test
    public void authenticateNegative(){
        Mockito.when(userDao.findUserByUsername(negativeAuthenticateUserPasswordIncorect.getUsername())).thenReturn(Optional.of(negativeAuthenticateUserPasswordIncorect));
        assertSame(negativeAuthenticateUserPasswordIncorect, userService.authenticate(negativeAuthenticateUserPasswordIncorect));
        Mockito.verify(userDao).findUserByUsername(negativeAuthenticateUserPasswordIncorect.getUsername());
    }

    @After
    public void tearDown(){
       // app.stop();
    }
}
