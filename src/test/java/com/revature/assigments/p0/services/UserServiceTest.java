package com.revature.assigments.p0.services;

import com.revature.assigments.p0.daos.UserDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class UserServiceTest {

    private UserService sut;
    private UserDAO mockUserDAO = mock(UserDAO.class);

    @Before
    public void setUp(){
    }

    @After
    public void tearDown(){

    }

    @Test
    public void test_isEmailValidWithValidEmail(){
        //Arrange
        String validEmail = "sample.address@domain.com";
        boolean expectedResult = true;

        when(mockUserDAO.isEmailAvailable(anyString())).thenReturn(true);

        //Act

        //Assert
        assertTrue(sut.isEmailValid(validEmail));

        }


    }

}
