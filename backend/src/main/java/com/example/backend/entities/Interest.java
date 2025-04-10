package com.example.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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

    @Column(name = "title", nullable = false)
    private String title;

}
