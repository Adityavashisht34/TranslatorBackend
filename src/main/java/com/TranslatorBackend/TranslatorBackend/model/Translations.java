// Translations.java (Model)
package com.TranslatorBackend.TranslatorBackend.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "translationsData")
public class Translations {

    @Id
    private String translationId;
    private String userId; // Store user reference
    private String fromLanguage;
    private String fromTranslation;
    private String toLanguage;
    private String toTranslation;
    private LocalDate date;

    public String getTranslationId() {
        return translationId;
    }

    public void setTranslationId(String translationId) {
        this.translationId = translationId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFromLanguage() {
        return fromLanguage;
    }

    public void setFromLanguage(String fromLanguage) {
        this.fromLanguage = fromLanguage;
    }

    public String getFromTranslation() {
        return fromTranslation;
    }

    public void setFromTranslation(String fromTranslation) {
        this.fromTranslation = fromTranslation;
    }

    public String getToLanguage() {
        return toLanguage;
    }

    public void setToLanguage(String toLanguage) {
        this.toLanguage = toLanguage;
    }

    public String getToTranslation() {
        return toTranslation;
    }

    public void setToTranslation(String toTranslation) {
        this.toTranslation = toTranslation;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
