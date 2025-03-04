package com.ty.mySpringBank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ty.mySpringBank.model.Account;
import com.ty.mySpringBank.model.Transaction;
import com.ty.mySpringBank.service.AccountService;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
@RequestMapping("/account")
public class AccountController {
	@Autowired
	private AccountService service;
	@GetMapping("/home")
	public String home() {
		return "about";
	}
	
	@GetMapping("/service")
	public String service() {
		return "service1";
	}
	
@GetMapping("/create")
	public String showcCeateAccount(Model model) {
		model.addAttribute("account", new Account());
		return "create-acount";
	}
@PostMapping("/account/create")
public String createAccount(@ModelAttribute Account account) {
	service.createAccount(account);
	return "redirect:/account/create";
}
@GetMapping("/deposit")
public String showDepositPage() {
	return "deposit";
}

@RequestMapping("/about")
public String showAbout() {
	return "about1";
}


@RequestMapping("/contact")
public String showContact() {
	return "contact";
}

@PostMapping("/account/deposit")
public String deposit(@RequestParam String accountNumber, @RequestParam int amount) {
  
	service.deposit(accountNumber, amount);
    return "redirect:/account/deposit";
}


@GetMapping("/withdraw")
public String showwithdrawPage() {
	return "withdraw";
}

@PostMapping("/account/withdraw")
public String withdraw(@RequestParam String accountNumber, @RequestParam int amount) {
  
	service.withdraw(accountNumber, amount);
    return "redirect:/account/withdraw";
}
@GetMapping("/transaction")
public String showForm() {
	return "tra-form";
}
@PostMapping("/transaction")
public String gettransaction(@RequestParam String accountNumber,Model model) {
	List<Transaction> list1=service.getTransaction(accountNumber);
	model.addAttribute("transaction", list1);
	return "tra-table";
}


@GetMapping("/checkbal")
public String showBalanceForm() {
	return "balanceform";
}


@PostMapping("/checkbalance")
public String checkBal(@RequestParam String accountNumber, Model model) {
  
	int bal=service.checkBalance(accountNumber);
	model.addAttribute("balance1", bal);
    return "balance";
}

}
