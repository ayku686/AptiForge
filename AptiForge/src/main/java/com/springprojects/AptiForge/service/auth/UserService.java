package com.springprojects.AptiForge.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springprojects.AptiForge.dao.UserRepo;
import com.springprojects.AptiForge.model.User;


@Service
public class UserService{
	@Autowired
	private UserRepo repo;
	
	public User saveUser(User user) {
		return repo.save(user);
	}
}