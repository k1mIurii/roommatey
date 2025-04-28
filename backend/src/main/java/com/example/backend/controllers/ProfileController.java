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
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/api/v1/profiles")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileMapper profileMapper;
    private final ProfileService profileService;

    @GetMapping(path = "/get/{id}")
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

    @DeleteMapping(path = "/delete")
    public ResponseEntity<?> deleteProfile(@RequestParam(name = "id", required = false) Long id,
                                           @RequestParam(name = "email", required = false) String email) {
        this.profileService.deleteProfileByEither(id, email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<ProfileDTO> getProfileByEmail(@RequestParam("email") String email) {
        Profile profileByEmail = this.profileService.getProfileByEmail(email);
        return new ResponseEntity<>(this.profileMapper.toDto(profileByEmail), HttpStatus.OK);
    }
}
