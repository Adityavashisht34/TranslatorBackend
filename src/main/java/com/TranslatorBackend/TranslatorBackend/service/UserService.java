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

    public ResponseEntity<User> registerUser(User user) {
        if(getUserByEmail(user.getEmail())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        user.setUserId(new ObjectId().toHexString());
         userRepository.save(user);
         return new ResponseEntity<>(user,HttpStatus.OK);
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
            if (checkPassword(user,password)) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Wrong password
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Email not found
    }

    private boolean getUserByEmail(String email){
        Optional<User> byEmail = userRepository.findByEmail(email);
        if(byEmail.isPresent()){
            return true;
        }
        return false;
    }
    private boolean checkPassword(User user, String password){
        if(user.getPassword().equals(password)){
            return true;
        }
        return false;
    }

    public String deleteUserById(String id){
        Optional<User> byId = userRepository.findById(id);
        if(byId.isPresent()){
            userRepository.deleteById(id);
        }
        return "Deleted";
    }

    public ResponseEntity<String> getPassword(String email){
        Optional<User> byEmail = userRepository.findByEmail(email);
        if(byEmail.isPresent()){
            return new ResponseEntity<>(byEmail.get().getPassword(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    public ResponseEntity<User> resetPassword(User user){
        Optional<User> byEmail = userRepository.findByEmail(user.getEmail());
        byEmail.get().setPassword(user.getPassword());
        saveUser(byEmail.get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}