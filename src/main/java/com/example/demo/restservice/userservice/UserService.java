package com.example.demo.restservice.userservice;

import com.example.demo.restservice.User;
import com.example.demo.restservice.repository.UserDocument;
import com.example.demo.restservice.repository.UserRepository;
import com.example.demo.restservice.utils.EntityMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDocument createUser(UserDocument userDocument) {
        return userRepository.save(userDocument);
    }

    public UserDocument getUser(String id) {
        return userRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));

    }

    public String deleteUser(String id) {
        userRepository.deleteById(id);
        return "Deleted user with id: " + id;
    }

    public UserDocument editUser(String id, User user) {
        UserDocument userDocument = userRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        if(!StringUtils.isEmpty(user.firstName())){
            userDocument.setFirstName(user.firstName());
        }
        if(!StringUtils.isEmpty(user.lastName())){
            userDocument.setLastName(user.lastName());
        }
        if(!StringUtils.isEmpty(user.emailAddress())){
            userDocument.setEmailAddress(user.emailAddress());
        }
        return userRepository.save(userDocument);
    }
}
