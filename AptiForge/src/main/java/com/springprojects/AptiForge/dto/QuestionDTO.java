package com.springprojects.AptiForge.dto;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.springprojects.AptiForge.model.Options;
import com.springprojects.AptiForge.model.Question;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Component
public class QuestionDTO{
	private UUID questionId;
	private UUID quizId;	
	private String content;
	private int penaltySeconds;
	private Timestamp createdAt;
    private List<UUID> optionIds;
	
    public QuestionDTO toDTO(Question question) {
    	QuestionDTO questionDTO = new QuestionDTO();
    	questionDTO.setQuestionId(question.getQuestionId());
    	questionDTO.setQuizId(question.getQuizId());
    	questionDTO.setContent(question.getContent());
    	questionDTO.setPenaltySeconds(question.getPenaltySeconds());
    	questionDTO.setCreatedAt(question.getCreatedAt());
    	questionDTO.setOptionIds(question.getOptions()
                               .stream()
                               .map(Options::getOptionId)
                               .collect(Collectors.toList()));
    	return questionDTO;
    }
}