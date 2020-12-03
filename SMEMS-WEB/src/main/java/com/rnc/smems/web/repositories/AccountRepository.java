package com.rnc.smems.web.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.rnc.smems.web.entities.Account;

public class AccountRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void save (Account account) {
		entityManager.persist(account);
	}
	
}
