package com.springprojects.AptiForge.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User{
	@Id
	@GeneratedValue(strategy=GenerationType.UUID)
	private UUID user_id;
	
	@Column(nullable = false, unique = true, length = 50)
	private String username;
	
	@Column(nullable = false, unique = true, length = 100)
	private String email;
	
	@Column(nullable = false, length = 255)
	private String password;
	
	@CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime created_at;
	
}