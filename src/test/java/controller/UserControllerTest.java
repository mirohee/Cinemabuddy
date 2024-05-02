package controller;

import model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class UserControllerTest {

    private static UserController userController;

    @BeforeAll
    public static void setUp() {
        userController = new UserController();
    }



    @Test
    void testRegisterUser() {
        User newUser = new User("test", "case", "testcase@example.com", "30", "abcef go");
        assertTrue(userController.registerUser(newUser));
        // Verify that the user was successfully added to the database
        assertNotNull(userController.getUserByEmail(newUser.getEmail()));
    }

    @Test
    void testRegisterUserDuplicateEmail() {
        User existingUser = new User("John", "Pork", "testuser@gmail.com", "25", "testpassword");
        assertFalse(userController.registerUser(existingUser));
    }

    @Test
    void testLoginUser() {
        userController.registerUser(new User("Jane", "Doe", "jane.doe@example.com", "30", "password"));
        assertTrue(userController.loginUser("jane.doe@example.com", "password"));
    }

    @Test
    void testLoginUserInvalidCredentials() {
        assertFalse(userController.loginUser("nonexistent@example.com", "password"));
    }

    @Test
    void testGetUserByEmail() {
        assertNotNull(userController.getUserByEmail("jane.doe@example.com"));
    }

    @Test
    void testGetUserByEmailNonexistent() {
        assertNull(userController.getUserByEmail("nonexistent@example.com"));
    }

    @Test
    void testDeleteUser() {
        assertTrue(userController.deleteUser("testcase@example.com"));
        // Verify that the user was successfully deleted from the database
        assertNull(userController.getUserByEmail("testcase@example.com"));
    }
}
