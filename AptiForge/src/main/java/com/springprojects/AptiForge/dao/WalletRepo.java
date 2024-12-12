package com.springprojects.AptiForge.dao;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springprojects.AptiForge.model.User;
import com.springprojects.AptiForge.model.Wallet;

@Repository
public interface WalletRepo extends JpaRepository<Wallet, UUID>{
	Optional<Wallet> findByUser(User user);
}