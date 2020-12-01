package com.rnc.smems.web.services;

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
		accountRepository.persist(account);
	}
	
	public List<Account> findAll() {
		List<Account> accounts = accountRepository.findAll();
		return accounts;
	}
	
}
