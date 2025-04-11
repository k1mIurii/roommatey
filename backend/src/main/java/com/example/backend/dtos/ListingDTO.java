package com.example.backend.dtos;

import com.example.backend.entities.Listing;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListingDTO {

    private Long id;

    private String title;

    private String description;

    private Integer price;

    private AddressDTO address;

    private LocalDate availableFrom;

    private LocalDate availableTo;

    private boolean isPetFriendly;

    private boolean isSmokeFree;

    public ListingDTO(Listing listing) {
        this.id = listing.getId();
        this.title = listing.getTitle();
        this.description = listing.getDescription();
        this.price = listing.getPrice();
        this.address = new AddressDTO(listing.getAddress());
        this.availableFrom = listing.getAvailableFrom();
        this.availableTo = listing.getAvailableTo();
        this.isPetFriendly = listing.getIsPetFriendly();
        this.isSmokeFree = listing.getIsSmokeFree();
    }
}
