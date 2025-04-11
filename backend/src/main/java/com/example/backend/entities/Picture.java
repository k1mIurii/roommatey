package com.example.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pictures")
@NoArgsConstructor
@AllArgsConstructor
public class Picture extends BaseEntity{

    @Column(name = "link")
    private String link;

    @Column(name = "main")
    private Boolean isMain = Boolean.FALSE;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "listing_id")
    private Listing listing;
}
