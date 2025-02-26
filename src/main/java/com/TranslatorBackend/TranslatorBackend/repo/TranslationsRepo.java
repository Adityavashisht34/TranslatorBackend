// TranslationsRepo.java
package com.TranslatorBackend.TranslatorBackend.repo;

import com.TranslatorBackend.TranslatorBackend.model.Translations;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface TranslationsRepo extends MongoRepository<Translations, String> {
    List<Translations> findByUserId(String userId); // Fetch translations by user
    Optional<Translations> findById(String translationId);
}
