package com.springprojects.AptiForge.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.springprojects.AptiForge.dao.QuizRepo;
import com.springprojects.AptiForge.dao.UserRepo;
import com.springprojects.AptiForge.dto.ParticipantDto;
import com.springprojects.AptiForge.dto.QuizDTO;
import com.springprojects.AptiForge.dto.TeamDTO;
import com.springprojects.AptiForge.model.Options;
import com.springprojects.AptiForge.model.Question;
import com.springprojects.AptiForge.model.Quiz;
import com.springprojects.AptiForge.model.Team;
import com.springprojects.AptiForge.model.User;

import jakarta.transaction.Transactional;

@Service
public class QuizService{
	
	@Autowired
	private QuizRepo quizRepository;
	
	@Autowired
	private UserRepo userRepository;
	
	@Autowired
    private RedisTemplate<String, ParticipantDto> participantRedisTemplate;
	
	@Autowired
    private RedisTemplate<String, QuizDTO> quizRedisTemplate;
	
	@Autowired
    private RedisTemplate<String, TeamDTO> teamRedisTemplate;

	@Transactional
	public void saveQuiz(Quiz quiz, String username) {
	    User createdBy = userRepository.findByUsername(username);
	    quiz.setCreatedBy(createdBy);

	    for (Question question : quiz.getQuestions()) {
	        question.setQuiz(quiz);
	        for (Options option : question.getOptions()) {
	            option.setQuestion(question);
	        }
	    }

	    for (Team team : quiz.getTeams()) {
	        team.setQuiz(quiz);
	    }
	    quizRepository.save(quiz);
	}

    
    
    @Transactional
    public List<Quiz> getAllQuiz(User user) {
    	return quizRepository.getAllQuiz(user.getUser_id());
    }
    
    public void storeQuizMetaData(String roomCode, QuizDTO quizDTO) {
    	quizRedisTemplate.opsForHash().put("quiz:"+roomCode,roomCode, quizDTO);
    }
    
    public void storeQuizParticipants(ParticipantDto participant,String roomCode) {
    	String key = "participants:"+roomCode;
    	participantRedisTemplate.opsForList().rightPush(key + ":participants", participant);
    }
    public void updateParticipantScore(String roomCode, UUID participantId, int score) {
    	String key = "participants:"+roomCode;
    	List<ParticipantDto> participants = participantRedisTemplate.opsForList().range(key + ":participants", 0, -1);
    	if (participants != null) {
            for (int i = 0; i < participants.size(); i++) {
                ParticipantDto participant = participants.get(i);
                if (participant.getUserId().equals(participantId)) {
                    participant.setScore(participant.getScore() + score);
                    participantRedisTemplate.opsForList().set(key, i, participant);
                    return;
                }
            }
        }
    }

    /*public void storeTeamScore(String roomCode, TeamDTO team) {
    	String key = "teams:"+roomCode;
        teamRedisTemplate.opsForList().rightPush(key + ":teams", team);
    }
    
    public void incrementTeamScore(String roomCode, String teamId, int teamScoreIncrement) {
        redisTemplate.opsForHash().increment("quiz:room:" + roomCode + ":teams", teamId, teamScoreIncrement);
    }*/
}