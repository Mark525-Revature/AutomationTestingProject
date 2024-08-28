package com.revature.service;

import java.util.Optional;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
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
    private User positiveCreatedUserSingleCharUsername;
    private User positiveCreatedUserSingleCharPassword;
    private User positiveCreatedUserSingleCharUsernameAndPassword;
    private User negativeUsernameTooLong;
    private User negativePasswordTooLong;
    private User userAlreadyRegistered;
    private User negativeAuthenticateUserPasswordIncorect;
    private User negativeNullUsername;
    private User negativeNullPassword;
    private User negativeNullUsernameAndPassword;

    

    @Before
    public void setUp() throws InterruptedException {
        Setup.resetTestDatabase();
        userDao = Mockito.mock(UserDao.class);
        userService = new UserServiceImp((userDao));
        positiveCreatedUser = new User(2, "Robin", "I am the night!!!");
        positiveCreatedUserSingleCharUsername = new User(2, "R", "I am the night!!!");
        positiveCreatedUserSingleCharPassword = new User(2, "Robin", "I");
        positiveCreatedUserSingleCharUsernameAndPassword = new User(2, "R", "I");
        negativeUsernameTooLong = new User(2, "Gordon and Clark are friends!!!", "I am the night!!!");
        negativePasswordTooLong = new User(2, "Robin", "Reverse Flash strikes again!!!!");
        userAlreadyRegistered = new User(2, "Batman", "I am the night!!!");
        negativeAuthenticateUserPasswordIncorect = new User(2, "Batman", "I am the day!!!");
        negativeNullUsername = new User(2, "", "I am the night!!!");
        negativeNullPassword = new User(2, "Robin", "");
        negativeNullUsernameAndPassword = new User(2, "", "");
    }

    @After
    public void tearDown(){}

    @Test
    public void createUserPositive(){
        Mockito.when(userDao.createUser(positiveCreatedUser)).thenReturn(Optional.of(positiveCreatedUser));
        assertTrue(userService.createUser(positiveCreatedUser).contains("Created user with username Robin and password I am the night!!!"));
        Mockito.verify(userDao).createUser(positiveCreatedUser);
    }

    @Test
        public void createUserPositiveSingleUsername(){
            Mockito.when(userDao.createUser(positiveCreatedUserSingleCharUsername)).thenReturn(Optional.of(positiveCreatedUserSingleCharUsername));
            assertTrue(userService.createUser(positiveCreatedUserSingleCharUsername).contains("Created user with username R and password I am the night!!!"));
            Mockito.verify(userDao).createUser(positiveCreatedUserSingleCharUsername);
        }

    @Test
    public void createUserPositiveSingleCharPassword(){
        Mockito.when(userDao.createUser(positiveCreatedUserSingleCharPassword)).thenReturn(Optional.of(positiveCreatedUserSingleCharPassword));
        assertTrue(userService.createUser(positiveCreatedUserSingleCharPassword).contains("Created user with username Robin and password I"));
        Mockito.verify(userDao).createUser(positiveCreatedUserSingleCharPassword);
    }

    @Test
    public void createUserPositiveSingleCharUsernameandPassword(){
        Mockito.when(userDao.createUser(positiveCreatedUserSingleCharUsernameAndPassword)).thenReturn(Optional.of(positiveCreatedUserSingleCharUsernameAndPassword));
        assertTrue(userService.createUser(positiveCreatedUserSingleCharUsernameAndPassword).contains("Created user with username R and password I"));
        Mockito.verify(userDao).createUser(positiveCreatedUserSingleCharUsernameAndPassword);
    }

    @Test
    public void createUserNegativeTooLongUsername(){
        UserFail e = assertThrows(UserFail.class, () -> {
            userService.createUser(negativeUsernameTooLong);
        });
        Assert.assertEquals("Username must be between 1 and 30 characters", e.getMessage());
    }

    @Test
    public void createUserNegativeTooPassword(){
        UserFail e = assertThrows(UserFail.class, () -> {
            userService.createUser(negativePasswordTooLong);
        });
        Assert.assertEquals("Password must be between 1 and 30 characters", e.getMessage());
    }

    @Test
    public void createUserNegativeNonUniqueUsername(){
        Mockito.when(userDao.findUserByUsername(userAlreadyRegistered.getUsername())).thenReturn(Optional.of(userAlreadyRegistered));
        Mockito.when(userDao.createUser(userAlreadyRegistered)).thenReturn(Optional.empty());
        UserFail e = assertThrows(UserFail.class, () -> {
            userService.createUser(userAlreadyRegistered);
        });
        Assert.assertEquals("Username is already in use", e.getMessage());
        Mockito.verify(userDao, Mockito.never()).createUser(userAlreadyRegistered);
    }

    @Test
    public void createUserNegativeNullUsername(){
        UserFail e = assertThrows(UserFail.class, () -> {
            userService.createUser(negativeNullUsername);
        });
        Assert.assertEquals("Username must be between 1 and 30 characters", e.getMessage());
    }

    @Test
    public void createUserNegativeNullPassword(){
        UserFail e = assertThrows(UserFail.class, () -> {
            userService.createUser(negativeNullPassword);
        });
        Assert.assertEquals("Password must be between 1 and 30 characters", e.getMessage());
    }

    @Test
    public void createUserNegativeNullUsernameandPassword(){
        UserFail e = assertThrows(UserFail.class, () -> {
            userService.createUser(negativeNullUsernameAndPassword);
        });
        Assert.assertEquals("Username must be between 1 and 30 characters", e.getMessage());
    }
    
    @Test
    public void authenticatePositive(){
        Mockito.when(userDao.findUserByUsername(userAlreadyRegistered.getUsername())).thenReturn(Optional.of(userAlreadyRegistered));
        assertSame(userAlreadyRegistered, userService.authenticate(userAlreadyRegistered));
        Mockito.verify(userDao).findUserByUsername(userAlreadyRegistered.getUsername());
    }
    
    @Test
    public void authenticateNegative(){
        Mockito.when(userDao.findUserByUsername(negativeAuthenticateUserPasswordIncorect.getUsername())).thenReturn(Optional.of(userAlreadyRegistered));
        UserFail e = assertThrows(UserFail.class, () -> {
            userService.authenticate(negativeAuthenticateUserPasswordIncorect);
        });
        Assert.assertEquals("Username and/or password do not match", e.getMessage());
        Mockito.verify(userDao).findUserByUsername(negativeAuthenticateUserPasswordIncorect.getUsername());
    }

}
