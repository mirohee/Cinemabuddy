package org.example;

import controller.UserController;
import model.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserControllerTest {

    private UserController userController;

    // The user created during registration for cleanup
    private User testUser;

    @Before
    public void setUp() {
        userController = new UserController();
    }


    @Test
    public void testUserRegistration() {
        // Test user registration
        testUser = new User("John", "Doe", "john.doe@example.com", "30", "password123");
        boolean registrationResult = userController.registerUser(testUser);

        assertTrue("User registration should be successful.", registrationResult);
    }

    @Test
    public void testUserLogin() {
        // Test user login
        String loginEmail = "john.doe@example.com";
        String loginPassword = "password123";
        boolean loginResult = userController.loginUser(loginEmail, loginPassword);

        assertTrue("User login should be successful.", loginResult);
    }

    @Test
    public void testInvalidUserLogin() {
        // Test user login with invalid credentials
        String loginEmail = "nonexistent.user@example.com";
        String loginPassword = "invalidPassword";
        boolean loginResult = userController.loginUser(loginEmail, loginPassword);

        assertFalse("User login with invalid credentials should fail.", loginResult);
    }
}
