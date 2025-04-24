package com.example.backend.services.impl;

import com.example.backend.entities.Hobby;
import com.example.backend.repositories.HobbyRepository;
import com.example.backend.services.HobbyService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HobbyServiceImpl implements HobbyService {

    private final HobbyRepository hobbyRepository;

    @Override
    @Cacheable("hobbies")
    public List<Hobby> getAllHobbies() {
        return this.hobbyRepository.findAll();
    }
}
