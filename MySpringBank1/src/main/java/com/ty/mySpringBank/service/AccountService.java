package com.ty.mySpringBank.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.mySpringBank.model.Account;
import com.ty.mySpringBank.model.Transaction;
import com.ty.mySpringBank.repository.AccountRepository;
import com.ty.mySpringBank.repository.TransactionRepository;

@Service
public class AccountService {
@Autowired
	private AccountRepository repository;
@Autowired
private TransactionRepository trarep;

public Account createAccount(Account account) {
	return repository.save(account);
}

public Account getAccountByNuber(String accountNumber) {
	
return	repository.findByAccountNumber(accountNumber);
}
	

public void deposit(String accountNumber, int amount) {
	Account account = repository.findByAccountNumber(accountNumber);
	account.setBalance(account.getBalance()+ amount);
	repository.save(account);
	Transaction transaction =new Transaction();
	transaction.setAccount(account);
	transaction.setAmount(amount);
	transaction.setType("deposit");
	transaction.setTimeStamp(new Date());
	trarep.save(transaction);
}

public void withdraw(String accountNumber, int amount) {
	Account account = repository.findByAccountNumber(accountNumber);
	if(account.getBalance()>=amount) {
	account.setBalance(account.getBalance()- amount);
	
	repository.save(account);
	
	Transaction transaction =new Transaction();
	transaction.setAccount(account);
	transaction.setAmount(amount);
	transaction.setType("withdraw");
	transaction.setTimeStamp(new Date());
	trarep.save(transaction);
}
	else
		throw new RuntimeException("insufficient balance");
}

public int checkBalance(String accountNumber) {
	Account account = repository.findByAccountNumber(accountNumber);
return account.getBalance();

}


public List<Transaction> getTransaction(String accountNumber){
	Account account= repository.findByAccountNumber(accountNumber);
	if(account!=null) {
		return trarep.findByAccountId(account.getId());
	}
	else return null;
}
}
