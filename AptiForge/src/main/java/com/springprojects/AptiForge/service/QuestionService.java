package com.springprojects.AptiForge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springprojects.AptiForge.dao.QuestionRepo;
import com.springprojects.AptiForge.model.Question;
@Service
public class QuestionService{
	@Autowired
	private QuestionRepo quesRepo;
	
	 public List<Question> findAll() {
		 return quesRepo.findAll();
	 }
	 
	 public List<Question> addQuestion(List<Question> questions) {
		 return quesRepo.saveAll(questions);
	 }
	 
	 public List<Question> getAllQuestions(){
		 return quesRepo.findAll();
	 }
}