package com.example.backend.controllers;

import com.example.backend.dtos.ProfileDTO;
import com.example.backend.services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "/api/v1/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping(name = "/{id}")
    public ResponseEntity<ProfileDTO> getProfile(@PathVariable Long id) {
        return null;
    }

    @PostMapping(name = "/create")
    public ResponseEntity<ProfileDTO> createProfile(@RequestBody ProfileDTO profileDTO) {
        return null;
    }

    @PutMapping(name = "/update/{id}")
    public ResponseEntity<ProfileDTO> updateProfile(@PathVariable Long id, @RequestBody ProfileDTO profileDTO) {
        return null;
    }

    @DeleteMapping(name = "/delete/{id}")
    public ResponseEntity<?> deleteProfile(@PathVariable Long id) {
        return null;
    }
}
