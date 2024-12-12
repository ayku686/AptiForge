package com.springprojects.AptiForge.service.auth;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springprojects.AptiForge.dao.UserRepo;
import com.springprojects.AptiForge.model.User;
import com.springprojects.AptiForge.model.UserPrincipal;
@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repo.findByUsername(username);
		if(user == null) {
			System.out.println("404 not found");
			throw new UsernameNotFoundException("User 404");
		}
		else {
			System.out.println(user.getUser_id());
		}
		return new UserPrincipal(user);
	}
	
}