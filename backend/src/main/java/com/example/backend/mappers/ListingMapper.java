package com.example.backend.mappers;

import com.example.backend.dtos.ListingDTO;
import com.example.backend.entities.Listing;

public interface ListingMapper {
    Listing fromDto(ListingDTO listingDTO);
    ListingDTO toDto(Listing listing);
}
