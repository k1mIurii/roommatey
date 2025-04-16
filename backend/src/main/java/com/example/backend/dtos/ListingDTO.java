package com.example.backend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private Integer price;

    @NotNull
    private AddressDTO address;

    @NotNull
    private LocalDate availableFrom;

    @NotNull
    private LocalDate availableTo;

    private boolean isPetFriendly;

    private boolean isSmokeFree;
}
