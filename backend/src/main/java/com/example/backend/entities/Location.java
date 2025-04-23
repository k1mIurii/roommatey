package com.example.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.*;

@Builder
@Getter
@Setter
@Entity
@Table(name = "locations", indexes = {
        @Index(name = "zip_idx", columnList = "zip"),
        @Index(name = "city_idx", columnList = "city")
})
@NoArgsConstructor
@AllArgsConstructor
public class Location extends BaseEntity {

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip", unique = true)
    private String zip;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;
}
