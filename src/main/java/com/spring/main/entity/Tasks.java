package com.spring.main.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Tasks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private String title;

    @Column
    private String description;

    @Column
    private boolean isCompleted;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}