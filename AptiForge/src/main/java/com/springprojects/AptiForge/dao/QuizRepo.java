package com.springprojects.AptiForge.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springprojects.AptiForge.model.Quiz;
import com.springprojects.AptiForge.model.User;

@Repository
public interface QuizRepo extends JpaRepository<Quiz, UUID>{
	@Query("select q from Quiz q where q.createdBy.id=:uuid")
	List<Quiz> getAllQuiz(@Param("uuid")UUID uuid);
}

