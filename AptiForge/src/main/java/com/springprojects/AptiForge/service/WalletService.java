package com.springprojects.AptiForge.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springprojects.AptiForge.dao.TransactionRepo;
import com.springprojects.AptiForge.dao.WalletRepo;
import com.springprojects.AptiForge.model.Transaction;
import com.springprojects.AptiForge.model.User;
import com.springprojects.AptiForge.model.Wallet;

@Service
public class WalletService{
	
	@Autowired
	private WalletRepo walletRepo;
	
	@Autowired
	private TransactionRepo transactionRepo;
	
	public void creditWallet(User user, BigDecimal amount, String description){
		Wallet wallet = walletRepo.findById(user.getUser_id()).orElseThrow(() -> new IllegalArgumentException("User doesn't have a wallet"));
		wallet.setBalance(wallet.getBalance().add(amount));
		
		Transaction transaction = new Transaction();
        transaction.setWallet(wallet);
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setTransactionType("credit");
        transaction.setTimestamp(new Timestamp(System.currentTimeMillis()));

        walletRepo.save(wallet);
        transactionRepo.save(transaction);
	}
	
	public void debitWallet(User user, BigDecimal amount, String description) {
		Wallet wallet = walletRepo.findById(user.getUser_id()).orElseThrow(() -> new IllegalArgumentException("User doesn't have a wallet"));

        wallet.setBalance(wallet.getBalance().subtract(amount));

        Transaction transaction = new Transaction();
        transaction.setWallet(wallet);
        transaction.setAmount(amount);
        transaction.setDescription(description);
        transaction.setTransactionType("debit");
        transaction.setTimestamp(new Timestamp(System.currentTimeMillis()));

        walletRepo.save(wallet);
        transactionRepo.save(transaction);
    }
	
	public List<Transaction> getTransactions(User user){
		Wallet wallet = walletRepo.findByUser(user).orElseThrow(() -> new IllegalArgumentException("Wallet not found"));
	    return wallet.getTransactions();
	}
	public BigDecimal getWalletBalance(User user) {
		Wallet wallet = walletRepo.findByUser(user).orElseThrow(() -> new IllegalArgumentException("User doesn't have a wallet"));
        return wallet.getBalance();
    }
}