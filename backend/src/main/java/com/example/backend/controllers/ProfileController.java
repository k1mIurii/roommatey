package com.example.backend.controllers;

import com.example.backend.dtos.ProfileDTO;
import com.example.backend.services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(name = "/api/v1/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping(name = "/{id}")
    public ResponseEntity<ProfileDTO> getProfile(@PathVariable Long id) {
        return new ResponseEntity<>(this.profileService.getProfileById(id), HttpStatus.OK);
    }

    @PostMapping(name = "/create")
    public ResponseEntity<ProfileDTO> createProfile(@RequestBody ProfileDTO profileDTO) {
        return new ResponseEntity<>(this.profileService.createProfile(profileDTO), HttpStatus.OK);
    }

    @PutMapping(name = "/update/{id}")
    public ResponseEntity<ProfileDTO> updateProfile(@PathVariable Long id, @RequestBody ProfileDTO profileDTO) {
        return new ResponseEntity<>(this.profileService.updateProfile(id, profileDTO), HttpStatus.OK);
    }

    @DeleteMapping(name = "/delete/{id}")
    public ResponseEntity<?> deleteProfile(@PathVariable Long id) {
        this.profileService.deleteProfile(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
