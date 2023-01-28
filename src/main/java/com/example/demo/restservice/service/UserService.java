package com.example.demo.restservice.service;

import com.example.demo.restservice.repository.UserDocument;
import com.example.demo.restservice.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

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

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public UserDocument editUser(String id, UserDocument user) {
        UserDocument userDocument = userRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        if(!StringUtils.isEmpty(user.getFirstName())){
            userDocument.setFirstName(user.getFirstName());
        }
        if(!StringUtils.isEmpty(user.getLastName())){
            userDocument.setLastName(user.getLastName());
        }
        if(!StringUtils.isEmpty(user.getEmailAddress())){
            userDocument.setEmailAddress(user.getEmailAddress());
        }
        return userRepository.save(userDocument);
    }
}
