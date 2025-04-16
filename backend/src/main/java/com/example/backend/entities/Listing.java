package com.example.backend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@Entity
@Table(name = "listings")
@NoArgsConstructor
@AllArgsConstructor
public class Listing extends BaseEntity {

    @OneToOne(mappedBy = "listing")
    private Profile profile;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "available_from")
    private LocalDate availableFrom;

    @Column(name = "available_to")
    private LocalDate availableTo;

    @Column(name = "pet_friendly")
    private Boolean isPetFriendly;

    @Column(name = "smoke_free")
    private Boolean isSmokeFree;
}
