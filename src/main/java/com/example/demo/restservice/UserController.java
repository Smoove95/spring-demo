package com.example.demo.restservice;

import com.example.demo.restservice.userservice.UserService;
import com.example.demo.restservice.utils.EntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
        return entityMapper.toAPI(userService.editUser(id, user));
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") String id){
        return entityMapper.toAPI(userService.getUser(id));
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") String id){
        return userService.deleteUser(id);
    }

}
