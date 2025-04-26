package com.example.backend.mappers.impl;

import com.example.backend.dtos.ListingDTO;
import com.example.backend.entities.Listing;
import com.example.backend.entities.Profile;
import com.example.backend.mappers.AddressMapper;
import com.example.backend.mappers.ListingMapper;
import com.example.backend.services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ListingMapperImpl implements ListingMapper {

    private final AddressMapper addressMapper;
    private final ProfileService profileService;

    @Override
    public Listing fromDto(ListingDTO listingDTO) {
        Profile profileById = profileService.getProfileById(listingDTO.getProfileId());
        if (null == profileById || profileById.getIsRoommateNeeded() == null || !profileById.getIsRoommateNeeded() || profileById.getIsHousingNeeded()) {
            throw new IllegalStateException(String.format("Profile with id: %s in illegal state", listingDTO.getProfileId()));
        }

        Listing listing = Listing.builder()
                .title(listingDTO.getTitle())
                .description(listingDTO.getDescription())
                .price(listingDTO.getPrice())
                .address(addressMapper.fromDto(listingDTO.getAddress()))
                .profile(profileById)
                .availableFrom(listingDTO.getAvailableFrom())
                .availableTo(listingDTO.getAvailableTo())
                .isPetFriendly(listingDTO.isPetFriendly())
                .isSmokeFree(listingDTO.isSmokeFree())
                .build();
        listing.setId(listingDTO.getId());
        return listing;
    }

    @Override
    public ListingDTO toDto(Listing listing) {
        return ListingDTO.builder()
                .id(listing.getId())
                .title(listing.getTitle())
                .description(listing.getDescription())
                .price(listing.getPrice())
                .address(
                        addressMapper.toDto(listing.getAddress())
                )
                .profileId(listing.getProfile().getId())
                .availableFrom(listing.getAvailableFrom())
                .availableTo(listing.getAvailableTo())
                .isPetFriendly(listing.getIsPetFriendly())
                .isSmokeFree(listing.getIsSmokeFree())
                .build();
    }
}
