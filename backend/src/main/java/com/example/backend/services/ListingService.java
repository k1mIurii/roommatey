package com.example.backend.services;

import com.example.backend.entities.Listing;

public interface ListingService {
    Listing getListingById(Long id);

    Listing createListing(Listing listing);

    Listing updateListing(Listing listing);

    Listing getListingByProfileId(Long profileId);

    void deactivateListingByProfileId(Long profileId);
}
