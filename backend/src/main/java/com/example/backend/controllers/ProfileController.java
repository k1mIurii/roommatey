package com.example.backend.controllers;

import com.example.backend.dtos.ProfileDTO;
import com.example.backend.entities.Profile;
import com.example.backend.mappers.ProfileMapper;
import com.example.backend.services.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileMapper profileMapper;
    private final ProfileService profileService;

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProfileDTO> getProfile(@PathVariable("id") Long id) {
        Profile profile = this.profileService.getProfileById(id);
        return new ResponseEntity<>(this.profileMapper.toDto(profile), HttpStatus.OK);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<ProfileDTO> createProfile(@Valid @RequestBody ProfileDTO profileDTO) {
        Profile profile = this.profileService.createProfile(this.profileMapper.fromDto(profileDTO));
        return new ResponseEntity<>(this.profileMapper.toDto(profile), HttpStatus.OK);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<ProfileDTO> updateProfile(@PathVariable("id") Long id, @Valid @RequestBody ProfileDTO profileDTO) {
        Profile profile = this.profileService.updateProfile(id, this.profileMapper.fromDto(profileDTO));
        return new ResponseEntity<>(this.profileMapper.toDto(profile), HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> deleteProfile(@PathVariable("id") Long id) {
        this.profileService.deleteProfileById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
