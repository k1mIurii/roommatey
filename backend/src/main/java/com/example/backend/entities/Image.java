package com.example.backend.entities;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@Entity
@Table(name = "images", indexes = {
        @Index(name = "link_idx", columnList = "link")
})
@NoArgsConstructor
@AllArgsConstructor
public class Image extends BaseEntity{

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
