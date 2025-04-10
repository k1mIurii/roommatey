package com.example.backend.entities;

import com.example.backend.enums.Action;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@Table(name = "interactions")
@NoArgsConstructor
@AllArgsConstructor
public class Interaction extends BaseEntity {

    @Column(name = "profile_id")
    private Long profileId;

    @Column(name = "profile_action")
    private Action profileAction;

    @Column(name = "target_profile_id")
    private Long targetProfileId;

    @Column(name = "target_profile_action")
    private Action targetProfileAction;

}
