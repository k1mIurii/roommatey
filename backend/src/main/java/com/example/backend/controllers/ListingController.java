package com.example.backend.controllers;

import com.example.backend.dtos.ListingDTO;
import com.example.backend.entities.Listing;
import com.example.backend.mappers.ListingMapper;
import com.example.backend.services.ListingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/listings")
@RequiredArgsConstructor
public class ListingController {

    private final ListingService listingService;

    private final ListingMapper listingMapper;


    @GetMapping(path = "/get/{profileId}")
    public ResponseEntity<ListingDTO> getListing(@PathVariable(name = "profileId") Long profileId){
        Listing listing = this.listingService.getListingByProfileId(profileId);
        return new ResponseEntity<>(this.listingMapper.toDto(listing), HttpStatus.OK);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<ListingDTO> createListing(@RequestBody @Valid ListingDTO listingDTO) {
        Listing listing = this.listingService.createListing(this.listingMapper.fromDto(listingDTO));
        return new ResponseEntity<>(this.listingMapper.toDto(listing), HttpStatus.OK);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<ListingDTO> updateListing(@RequestBody @Valid ListingDTO listingDTO) {
        Listing listing = this.listingService.updateListing(this.listingMapper.fromDto(listingDTO));
        return new ResponseEntity<>(this.listingMapper.toDto(listing), HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{profileId}")
    public ResponseEntity<?> deleteListing(@PathVariable(name = "profileId") Long profileId) {
        this.listingService.deactivateListingByProfileId(profileId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
