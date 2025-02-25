package com.TranslatorBackend.TranslatorBackend.controller;

import com.TranslatorBackend.TranslatorBackend.model.User;
import com.TranslatorBackend.TranslatorBackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<User> getUserById(@RequestBody User user) {
        Optional<User> userById = userService.getUserById(user.getUserId());
        if(userById.isPresent()){
            return new ResponseEntity<>(userById.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/email")
    public ResponseEntity<User> getUserByEmail(@RequestBody User user) {
        return userService.getUserByEmail(user.getEmail(), user.getPassword());
    }

}
