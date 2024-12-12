package com.springprojects.AptiForge.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springprojects.AptiForge.model.Transaction;
import com.springprojects.AptiForge.model.User;
import com.springprojects.AptiForge.model.UserPrincipal;
import com.springprojects.AptiForge.service.WalletService;

@RestController
public class WalletController{
	
	@Autowired
	WalletService walletService;
	
	
	@GetMapping("/wallet/balance")
	public BigDecimal getWalletBalance() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal)authentication.getPrincipal();
        User currentUser = userPrincipal.getUser();
	    return walletService.getWalletBalance(currentUser);
	}

	@GetMapping("/wallet/transactions")
	public List<Transaction> getTransactionHistory() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal)authentication.getPrincipal();
        User currentUser = userPrincipal.getUser();
		return walletService.getTransactions(currentUser);
	}

}