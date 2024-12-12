package com.springprojects.AptiForge.controller.quiz;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springprojects.AptiForge.dto.QuestionDTO;
import com.springprojects.AptiForge.model.Question;
import com.springprojects.AptiForge.model.User;
import com.springprojects.AptiForge.model.UserPrincipal;
import com.springprojects.AptiForge.service.QuestionService;

@RestController()
public class QuestionController{
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private QuestionDTO questionDTO;
	
	@PostMapping("api/question/upload")
	public ResponseEntity<?> uploadQuestion(List<Question> questions){
		try {
			List<Question> addedQuestions = questionService.addQuestion(questions);
			return addedQuestions!=null?ResponseEntity.ok("Question saved successfully!"):ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Couldn't add questions");
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occured : "+e);
		}
	}
	
	@GetMapping("api/question/getallquestions")
	public ResponseEntity<?> getAllQuestions(){
    	try {
    		List<QuestionDTO> allQuestions = new ArrayList<>();
    		for(Question question:questionService.findAll())
    				allQuestions.add(questionDTO.toDTO(question));
            return ResponseEntity.ok(allQuestions);
    	}
        catch(Exception e){
        	System.out.println(e);
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occured : "+e);
        }
    }
}