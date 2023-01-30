package com.example.demo.controller;

import com.example.demo.BaseIntegrationTest;
import com.example.demo.DemoApplication;
import com.example.demo.restservice.controllers.UserController;
import com.example.demo.restservice.models.User;
import com.example.demo.restservice.repository.UserRepository;
import com.example.demo.restservice.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class UserControllerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private UserController userController;

    @Autowired
    private UserRepository userRepository;



    @Test
    public void testCreateUser(){
        User user = new User(null, "Tom", "Test123", "blabla@123.com");
        String userId = userController.createUser(user).id();
        assertNotNull(userId);

        User returnedUser = userController.getUser(userId);
        assertEquals(returnedUser.firstName(), user.firstName());
        assertEquals(returnedUser.lastName(), user.lastName());
        assertEquals(returnedUser.emailAddress(), user.emailAddress());
    }

    @Test
    public void testCreateAndUpdateUser(){
        User user = new User(null, "Test123", "Test1234", "blabla@1234.com");
        String userId = userController.createUser(user).id();
        assertNotNull(userId);

        userController.editUser(userId, new User(null, "Test567", null, null));
        User returnedUser = userController.getUser(userId);
        assertEquals("Test567", returnedUser.firstName());
        assertEquals(returnedUser.lastName(), user.lastName());
        assertEquals(returnedUser.emailAddress(), user.emailAddress());
    }

    @Test
    public void testDeleteUser(){
        User user = new User(null, "TestDelete", "TestDelete12", "blabla@1234.com");
        String userId = userController.createUser(user).id();
        assertNotNull(userId);

        userController.deleteUser(userId);
        HttpClientErrorException httpClientErrorException = Assertions.assertThrows(HttpClientErrorException.class, () -> userController.getUser(userId));
        assertEquals(404, httpClientErrorException.getStatusCode().value());

    }
}
