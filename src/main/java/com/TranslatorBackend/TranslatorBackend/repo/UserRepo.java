package com.TranslatorBackend.TranslatorBackend.repo;

import com.TranslatorBackend.TranslatorBackend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<User,String> {
     Optional<User> findByEmail(String email);
     void deleteById(String id);
}
