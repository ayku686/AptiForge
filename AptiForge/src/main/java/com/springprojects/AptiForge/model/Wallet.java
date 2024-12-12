package com.springprojects.AptiForge.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "wallet")
public class Wallet{
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID walletId;
	
	@OneToOne
    @JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	@Column(nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;
	
	@OneToMany(mappedBy = "wallet", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Transaction> transactions = new ArrayList<>();
}