package com.rnc.smems.web.beans;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.rnc.smems.web.entities.Account;
import com.rnc.smems.web.enums.accountRole;
import com.rnc.smems.web.services.AccountService;


@Named
@ViewScoped
public class SecurityBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String username;
	
	private String password;
	
	private String message;
	
	private Account account;
	
	@Inject
	private AccountService accountservice; 
	
	public void signIn() throws IOException {
		Account account = accountservice.findByUsernameAndPassword(username, password);
		if (account == null) {
			message = "Username and Password do not match.";
		
		} else {
			if(account.getAccountRole().equals(accountRole.Admin))
				FacesContext.getCurrentInstance().getExternalContext().redirect("admin/account.xhtml");
			else if (account.getAccountRole().equals(accountRole.HR_Manager))
				FacesContext.getCurrentInstance().getExternalContext().redirect("hr/account.xhtml");
			else if (account.getAccountRole().equals(accountRole.Warehouse))
				FacesContext.getCurrentInstance().getExternalContext().redirect("warehouse/product.xhtml");
		}
	}
	
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
}
