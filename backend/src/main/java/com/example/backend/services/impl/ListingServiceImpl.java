package com.example.backend.services.impl;

import com.example.backend.entities.Listing;
import com.example.backend.exceptions.RecordNotFoundException;
import com.example.backend.repositories.ListingRepository;
import com.example.backend.services.ListingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ListingServiceImpl implements ListingService {
    
    private final ListingRepository listingRepository;
    
    @Override
    public Listing createListing(Listing listing) {
        return this.listingRepository.save(listing);
    }

    @Override
    public Listing updateListing(Listing listing) {
        Listing toUpdate = this.listingRepository.findByIdAndDeletedAtIsNull(listing.getId())
                .orElseThrow(() -> new RecordNotFoundException(String.format("Listing with id %s not found", listing.getId())));
        BeanUtils.copyProperties(listing, toUpdate, "id");
        return this.listingRepository.save(toUpdate);
    }

    @Override
    public Listing getListingByProfileId(Long profileId) {
        return this.listingRepository.findByProfileIdAndDeletedAtIsNull(profileId)
                .orElseThrow(() -> new RecordNotFoundException(String.format("Listing with id %s not found", profileId)));
    }

    @Override
    public void deactivateListingByProfileId(Long profileId) {
        Listing toDelete = this.listingRepository.findByProfileIdAndDeletedAtIsNull(profileId)
                .orElseThrow(() -> new RecordNotFoundException(String.format("Listing with id %s not found", profileId)));
        toDelete.setDeletedAt(LocalDateTime.now());
        this.listingRepository.save(toDelete);
    }
}
