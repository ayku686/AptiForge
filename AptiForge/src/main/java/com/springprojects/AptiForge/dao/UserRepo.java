package com.springprojects.AptiForge.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springprojects.AptiForge.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, UUID>{
	User findByUsername(String username);
}