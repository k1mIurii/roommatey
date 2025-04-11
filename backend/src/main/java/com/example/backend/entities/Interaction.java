package com.example.backend.entities;

import com.example.backend.enums.Action;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@Table(name = "interactions")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Interaction {

    //For batch inserts
    @Id
    @SequenceGenerator(name = "id_seq_gen", allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_seq_gen")
    private Long id;

    @Column(name = "profile_id")
    private Long profileId;

    @Column(name = "profile_action")
    private Action profileAction;

    @Column(name = "target_profile_id")
    private Long targetProfileId;

    @Column(name = "target_profile_action")
    private Action targetProfileAction;

}
