package com.springprojects.AptiForge.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springprojects.AptiForge.model.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction,UUID>{
	
}