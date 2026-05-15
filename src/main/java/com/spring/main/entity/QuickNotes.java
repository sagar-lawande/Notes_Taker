package com.spring.main.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class QuickNotes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}