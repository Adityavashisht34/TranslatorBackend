// TranslationController.java
package com.TranslatorBackend.TranslatorBackend.controller;

import com.TranslatorBackend.TranslatorBackend.model.Translations;
import com.TranslatorBackend.TranslatorBackend.service.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/translations")
public class TranslationController {
    @Autowired
    private TranslationService translationService;

    // Save Translation for Logged-In User
    @PostMapping("/save/{userId}")
    public ResponseEntity<Translations> saveTranslation(@RequestBody Translations translation,
                                                        @PathVariable String userId) {
        return translationService.saveTranslation(translation, userId);
    }

    // Fetch User's Translation History
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Translations>> getUserTranslations(@PathVariable String userId) {
        return translationService.getTranslationsByUserId(userId);
    }
}
