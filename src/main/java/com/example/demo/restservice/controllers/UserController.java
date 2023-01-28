package com.example.demo.restservice.controllers;

import com.example.demo.restservice.models.User;
import com.example.demo.restservice.service.UserService;
import com.example.demo.restservice.utils.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;
    @Autowired
    private EntityMapper entityMapper;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        return entityMapper.toAPI(userService.createUser(entityMapper.toEntity(user)));
    }

    @PatchMapping(path = "/users/{id}")
    public User editUser(@PathVariable("id") String id, @RequestBody User user){
        return entityMapper.toAPI(userService.editUser(id, entityMapper.toEntity(user)));
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") String id){
        return entityMapper.toAPI(userService.getUser(id));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") String id){
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted with id: " + id);
    }

}
