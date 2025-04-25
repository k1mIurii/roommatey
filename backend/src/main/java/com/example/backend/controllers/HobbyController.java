package com.example.backend.controllers;

import com.example.backend.entities.Hobby;
import com.example.backend.services.HobbyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/hobbies")
@RequiredArgsConstructor
public class HobbyController {

    private final HobbyService hobbyService;

    @GetMapping
    public ResponseEntity<List<Hobby>> getAllHobbies() {
        return ResponseEntity.ok(hobbyService.getAllHobbies());
    }
}
