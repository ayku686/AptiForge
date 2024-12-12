package com.springprojects.AptiForge.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor
@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID teamId;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int teamScore;
    
    @JsonProperty("isWinner")
    @Column(nullable = false)
    private boolean isWinner;
    
    @PrePersist
    private void setDefaultName() {
        if (this.name == null || this.name.isEmpty()) {
            this.name = "Team " + this.teamId;
        }
    }
}
