package com.rnc.smems.web.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.rnc.smems.web.entities.Account;

public class AccountRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void save (Account account) {
		entityManager.persist(account);
	}
	
	public List<Account> findAll() {
		String str = "select t from %s t"; // select account from account;
		String sql = String.format(str, Account.class.getSimpleName());
		TypedQuery<Account> query = entityManager.createQuery(sql, Account.class);
		return query.getResultList();
	}
	
}
