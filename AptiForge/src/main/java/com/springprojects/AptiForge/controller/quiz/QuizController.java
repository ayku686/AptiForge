package com.springprojects.AptiForge.controller.quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springprojects.AptiForge.dao.UserRepo;
import com.springprojects.AptiForge.dto.QuizDTO;
import com.springprojects.AptiForge.model.Quiz;
import com.springprojects.AptiForge.model.User;
import com.springprojects.AptiForge.model.UserPrincipal;
import com.springprojects.AptiForge.service.QuizService;

@RestController
public class QuizController{
	
	@Autowired
	private QuizService quizService;
	
	@Autowired
	private QuizDTO quizDto;

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Autowired
	private UserRepo userRepository;
	
    @PostMapping("/api/save_quiz")
    public ResponseEntity<String> saveQuiz(@RequestBody Quiz quiz) {
    	try {
    		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
    		quizService.saveQuiz(quiz, username);
    		return ResponseEntity.ok("Quiz saved successfully!");
    	}
        catch(Exception e){
        	System.out.println(e);
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occured : "+e);
        }
    }
    
    @GetMapping("/api/allquiz")
    public ResponseEntity<?> getAllQuiz(){
    	try {
    		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserPrincipal userPrincipal = (UserPrincipal)authentication.getPrincipal();
            User currentUser = userPrincipal.getUser();
            List<QuizDTO> allQuiz = new ArrayList<>();
            for(Quiz quiz:quizService.getAllQuiz(currentUser)) {
            	allQuiz.add(quizDto.toDTO(quiz));
            }
            return ResponseEntity.ok(allQuiz);
    	}
        catch(Exception e){
        	System.out.println(e);
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occured : "+e);
        }
    }
    
    @PostMapping("api/create_quiz")
    public ResponseEntity<?> createQuiz(@RequestBody QuizDTO quizData){
    	String roomCode = UUID.randomUUID().toString().substring(0,6).toUpperCase();
    	quizData.setRoomCode(roomCode);  	
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	User user = userRepository.findByUsername(authentication.getName());
	    quizData.setCreatedBy(user.getUser_id());
	    quizService.storeQuizMetaData(roomCode, quizData);
    	return ResponseEntity.ok("Quiz created successfully. Room Code: " + roomCode);
    }
}