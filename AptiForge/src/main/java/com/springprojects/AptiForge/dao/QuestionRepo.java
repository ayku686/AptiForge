package com.springprojects.AptiForge.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springprojects.AptiForge.model.Question;

@Repository
public interface QuestionRepo extends JpaRepository<Question, UUID>{
	List<Question> findAll();
}