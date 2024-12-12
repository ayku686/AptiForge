package com.springprojects.AptiForge.controller.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springprojects.AptiForge.dao.WalletRepo;
import com.springprojects.AptiForge.model.User;
import com.springprojects.AptiForge.model.Wallet;
import com.springprojects.AptiForge.service.auth.UserService;


@RestController
public class UserController{
	
	@Autowired
	private UserService service;
	
	@Autowired
	private WalletRepo walletRepo;
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	@PostMapping("register")
	public ResponseEntity<String> register(@RequestBody User user) {
		user.setPassword(encoder.encode(user.getPassword()));
		service.saveUser(user);
		Wallet wallet = new Wallet();
	    wallet.setUser(user);
	    walletRepo.save(wallet);
	    return ResponseEntity.ok("User created successfully");
	}
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@PostMapping("login")
	public String login(@RequestBody User user) {
		Authentication authentication = 
				authenticationManager.authenticate(
						new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
		if(authentication.isAuthenticated())
			return "Success";
		else
			return "Login Failed";
	}
	
	@GetMapping("/")
	public String home() {
		return "Welcome";
	}
}