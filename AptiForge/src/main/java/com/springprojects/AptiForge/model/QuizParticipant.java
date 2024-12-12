package com.springprojects.AptiForge.model;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity
@Table(name = "quiz_participant")
public class QuizParticipant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID participantId;


    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private Quiz quiz;
    
    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;
    
    @Column(nullable = true)
    private int bountyAmount;
    
    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int individualScore;
    
    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int completionTime;
    
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean isWinner;
}
