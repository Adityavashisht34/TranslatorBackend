// TranslationService.java
package com.TranslatorBackend.TranslatorBackend.service;

import com.TranslatorBackend.TranslatorBackend.model.Translations;
import com.TranslatorBackend.TranslatorBackend.model.User;
import com.TranslatorBackend.TranslatorBackend.repo.TranslationsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TranslationService {
    @Autowired
    private TranslationsRepo translationsRepo;

    @Autowired
    private UserService userService;

    // Save Translation for a Specific User
    public ResponseEntity<Translations> saveTranslation(Translations translation, String userId) {
        Optional<User> userOptional = userService.getUserById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Set user ID and other properties
            translation.setUserId(user.getUserId());
            translation.setDate(LocalDate.now());

            // Save translation
            Translations savedTranslation = translationsRepo.save(translation);

            // Update user with new translation
            user.getList().add(savedTranslation);
            userService.saveUser(user);

            return new ResponseEntity<>(savedTranslation, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Get All Translations for a Specific User
    public ResponseEntity<List<Translations>> getTranslationsByUserId(String userId) {
        List<Translations> translations = translationsRepo.findByUserId(userId);
        return new ResponseEntity<>(translations, HttpStatus.OK);
    }
}
