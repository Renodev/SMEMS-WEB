package com.rnc.smems.web.beans;

import java.io.Serializable;

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
	
	// configAccount.xhtml JSF View create object for ConfigAccountBean
	// ConfigAccountBean configAccountBean = new ConfigAccountBean();
	
	// object Declaration
	private Account account; // member object
	
	@Inject
	private AccountService accountService;
	
	@PostConstruct
	public void initialize () {
		// account object initialization
		account = new Account(); // statefull
	}
	
	public void save () {
		accountService.save(account);
	}
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
