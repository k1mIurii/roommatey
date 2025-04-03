package com.example.backend.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "messages")
@NoArgsConstructor
@AllArgsConstructor
public class Message extends BaseEntity{

    @OneToOne
    @JoinColumn(name = "sender_id")
    private Profile sender;

    @OneToOne
    @JoinColumn(name = "receiver_id")
    private Profile receiver;

    @Column(name = "message")
    private String message;

    @Column(name = "received")
    private Boolean wasReceived;
}
