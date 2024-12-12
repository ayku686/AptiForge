package com.springprojects.AptiForge.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.springprojects.AptiForge.model.Question;
import com.springprojects.AptiForge.model.Quiz;
import com.springprojects.AptiForge.model.Team;

import lombok.Data;

@Data
@Component
public class QuizDTO implements Serializable{
	private UUID quizID;
    private String title;
    private int durationSeconds;
    private UUID createdBy;
    private boolean randomTeammates;
    private String roomCode;
    private int noOfTeams;
    private BigDecimal totalBounty;
    private boolean isPublic;
    private Timestamp startTime;
    private Timestamp endTime;
    private List<UUID> questionIds = new ArrayList<>();
    private List<UUID> teamIds = new ArrayList<>();
    
    public QuizDTO toDTO(Quiz quiz) {
        QuizDTO dto = new QuizDTO();
        dto.setQuizID(quiz.getQuizID());
        dto.setTitle(quiz.getTitle());
        dto.setDurationSeconds(quiz.getDurationSeconds());
        dto.setCreatedBy(quiz.getCreatedById());
        dto.setRandomTeammates(quiz.isRandomTeammates());
        dto.setRoomCode(quiz.getRoomCode());
        dto.setNoOfTeams(quiz.getNoOfTeams());
        dto.setTotalBounty(quiz.getTotalBounty());
        dto.setPublic(quiz.isPublic());
        dto.setStartTime(quiz.getStartTime());
        dto.setEndTime(quiz.getEndTime());
        dto.setQuestionIds(quiz.getQuestions()
                               .stream()
                               .map(Question::getQuestionId)
                               .collect(Collectors.toList()));
        dto.setTeamIds(quiz.getTeams()
                           .stream()
                           .map(Team::getTeamId)
                           .collect(Collectors.toList()));
        return dto;
    }
}