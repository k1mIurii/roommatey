package com.example.backend.dtos;

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
}
