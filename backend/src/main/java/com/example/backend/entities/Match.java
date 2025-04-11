package com.example.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "matches")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Match extends BaseEntity {

    @Column(name = "profile_id_1")
    private Long profileId1;

    @Column(name = "profile_id_2")
    private Long profileId2;

}
