package com.TranslatorBackend.TranslatorBackend.service;

import com.TranslatorBackend.TranslatorBackend.model.User;
import com.TranslatorBackend.TranslatorBackend.repo.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;

    public User registerUser(User user) {
        user.setUserId(new ObjectId().toHexString());
        return userRepository.save(user);
    }

    public User saveUser(User user){
        userRepository.save(user);
        return user;
    }

    public Optional<User> getUserById(String userId) {
        Optional<User> byId = userRepository.findById(userId);
        if(byId.isPresent()){
            return byId;
        }
        return null;
    }

    public ResponseEntity<User> getUserByEmail(String email, String password) {
        Optional<User> byEmail = userRepository.findByEmail(email);
        if (byEmail.isPresent()) {
            User user = byEmail.get();
            if (user.getPassword().equals(password)) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Wrong password
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Email not found
    }

    public String deleteUserById(String id){
        Optional<User> byId = userRepository.findById(id);
        if(byId.isPresent()){
            userRepository.deleteById(id);
        }
        return "Deleted";
    }
}
