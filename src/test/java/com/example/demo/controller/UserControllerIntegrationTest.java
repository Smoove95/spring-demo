package com.example.demo.controller;

import com.example.demo.BaseIntegrationTest;
import com.example.demo.DemoApplication;
import com.example.demo.restservice.controllers.UserController;
import com.example.demo.restservice.models.User;
import com.example.demo.restservice.repository.UserRepository;
import com.example.demo.restservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class UserControllerIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private UserController userController;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void loadContext(){

    }

    @Test
    public void testCreateAndGetUser(){
        User user = new User(null, "Tom", "Test123", "blabla@123.com");
        User user1 = userController.createUser(user);
        assertNotNull(user1);
        assertNotNull(user1.id());

        User user2 = userController.getUser(user1.id());
        assertEquals(user2.id(), user1.id());


    }
}
