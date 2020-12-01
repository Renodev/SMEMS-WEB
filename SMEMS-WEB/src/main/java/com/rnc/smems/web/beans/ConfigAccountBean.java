package com.rnc.smems.web.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rnc.smems.web.entities.Account;
import com.rnc.smems.web.services.AccountService;

@Named
@ViewScoped
public class ConfigAccountBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Account account;
	
	private List<Account> accounts;
	
	@Inject
	private AccountService accountService;
	
	@PostConstruct
	public void initialize () {
		account = new Account();
		accounts = accountService.findAll();
	}
	
	public void save ()  {
		account.setId(LocalDateTime.now().toString());
		accountService.save(account);
		initialize();
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

}
