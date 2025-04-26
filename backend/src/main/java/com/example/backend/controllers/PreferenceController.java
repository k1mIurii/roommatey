package com.example.backend.controllers;

import com.example.backend.dtos.PreferenceDTO;
import com.example.backend.entities.Preference;
import com.example.backend.mappers.PreferenceMapper;
import com.example.backend.services.PreferenceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/preferences")
@RequiredArgsConstructor
public class PreferenceController {

    private final PreferenceService preferenceService;

    private final PreferenceMapper preferenceMapper;

    @GetMapping(path = "/get/{profileId}")
    public ResponseEntity<PreferenceDTO> getProfile(@PathVariable(name = "profileId") Long profileId) {
        Preference preference = this.preferenceService.getByProfileId(profileId);
        return new ResponseEntity<>(this.preferenceMapper.toDto(preference), HttpStatus.OK);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<PreferenceDTO> createPreference(@RequestBody @Valid PreferenceDTO preferenceDTO) {
        Preference preference = this.preferenceService.create(this.preferenceMapper.fromDto(preferenceDTO));
        return new ResponseEntity<>(this.preferenceMapper.toDto(preference), HttpStatus.OK);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<PreferenceDTO> updatePreference(@RequestBody @Valid PreferenceDTO preferenceDTO) {
        Preference preference = this.preferenceService.update(this.preferenceMapper.fromDto(preferenceDTO));
        return new ResponseEntity<>(this.preferenceMapper.toDto(preference), HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{profileId}")
    public ResponseEntity<?> deletePreference(@PathVariable(name = "profileId") Long profileId) {
        this.preferenceService.deleteByProfileId(profileId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
