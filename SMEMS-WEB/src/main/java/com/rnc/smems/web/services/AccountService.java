package com.rnc.smems.web.services;

import java.time.LocalDateTime;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.rnc.smems.web.entities.Account;
import com.rnc.smems.web.repositories.AccountRepository;

@LocalBean
@Stateless
public class AccountService {
	
	@Inject
	private AccountRepository accountRepository;
	
	public void save (Account account) {
		account.setId(LocalDateTime.now().toString());
		accountRepository.save(account);
	}
	
	public void update (Account account) {
		
	}
	
	public void delete (Account account) {
		
	}
	
	public List<Account> findAll() {
		return null;
	}
		
}
