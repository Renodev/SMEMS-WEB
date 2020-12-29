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
		if (account.getId() == 0) {
			accountRepository.save(account);
		} else {
			accountRepository.update(account);
		}
	}
	
	public void delete (Account account) {
		Account acc = findByID(account.getId());
		accountRepository.delete(acc);
	}
	
	public Account findByID (long id) {
		return accountRepository.findByID(id);
	}
	
	public List<Account> findAll() {
		return accountRepository.findAll();
	}

	public Account findByUsernameAndPassword (String username, String passsword) {
		return accountRepository.findByUsernameAndPassword(username, passsword);
	}
}
