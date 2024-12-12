package com.springprojects.AptiForge.model;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity
@Table(name = "question")
public class Question{
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID questionId;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "quiz_id",nullable = true)
	private Quiz quiz;	
	
	@JsonProperty("quizId")
	public UUID getQuizId() {
		return quiz!=null?quiz.getQuizID():null;
	}
	
	@Column(columnDefinition = "TEXT", nullable = false)
	private String content;
	
	private int penaltySeconds;
	
	@Column(nullable = false, updatable = false)
	private Timestamp createdAt;
	
	@JsonProperty("options")
	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Options> options = new ArrayList<>();
	
	@JsonProperty("availableOptions")
	public List<UUID> getOptionIds() {
    	List<UUID> allOptions = new ArrayList<>();
    	for(Options option:options) {
    		allOptions.add(option.getOptionId());
    	}
        return allOptions;
    }
}