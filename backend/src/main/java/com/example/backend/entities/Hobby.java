package com.example.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "hobbies", indexes = {
        @Index(name = "title_idx", columnList = "title")
})
@NoArgsConstructor
@AllArgsConstructor
public class Hobby extends BaseEntity {

    @Column(name = "title", nullable = false)
    private String title;

}
