package com.example.demo.restservice.utils;

import com.example.demo.restservice.User;
import com.example.demo.restservice.repository.UserDocument;
import org.springframework.stereotype.Component;

@Component
public class EntityMapper {

    public UserDocument toEntity(User user){
        UserDocument userDocument = new UserDocument();
        userDocument.setFirstName(user.firstName());
        userDocument.setLastName(user.lastName());
        userDocument.setEmailAddress(user.emailAddress());
        return userDocument;
    }

    public User toAPI(UserDocument userDocument){
        return new User(userDocument.getId(), userDocument.getFirstName(), userDocument.getLastName(), userDocument.getEmailAddress());
    }

}
