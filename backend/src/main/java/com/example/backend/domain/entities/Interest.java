package com.example.backend.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "interests")
@NoArgsConstructor
@AllArgsConstructor
public class Interest extends BaseEntity {

    @Column(name = "name")
    private String name;

}
