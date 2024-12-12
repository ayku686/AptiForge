package com.springprojects.AptiForge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID quizID;

    @Column(nullable = true, length = 200)
    private String title;

    @Column(nullable = false)
    private int durationSeconds;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    @JsonIgnore
    private User createdBy;

    @JsonProperty("createdById") 
    public UUID getCreatedById() {
        return createdBy != null ? createdBy.getUser_id() : null;
    }

    @Column(nullable = false, updatable = false)
    private boolean randomTeammates;

    @Column(length = 10, nullable = false, unique = true)
    private String roomCode;

    @Column(nullable = true)
    private int noOfTeams;

    @Column(nullable = true)
    private BigDecimal totalBounty;

    @Column(nullable = false)
    private boolean isPublic;

    @Column(nullable = false, updatable = false)
    private Timestamp startTime;

    @Column(nullable = false, updatable = false)
    private Timestamp endTime;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty("questions")
    @ToString.Exclude
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty("teams")
    @ToString.Exclude
    private List<Team> teams = new ArrayList<>();
}
