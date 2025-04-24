package com.example.backend.services.impl;

import com.example.backend.entities.Language;
import com.example.backend.repositories.jpa.LanguageRepository;
import com.example.backend.services.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;

    @Override
    @Cacheable("languages")
    public List<Language> getAllLanguages() {
        return this.languageRepository.findAll();
    }

}
