package com.rnc.smems.web.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.rnc.smems.web.entities.Account;

public class AccountRepository {
	
	@PersistenceContext
	private EntityManager entityManger;

	public void persist (Account account) {
		entityManger.persist(account);
	}
	
	public List<Account> findAll () {
		String sql = String.format("select t from %s t", Account.class.getSimpleName());
		TypedQuery<Account> query = entityManger.createQuery(sql, Account.class);
		return query.getResultList();
	}
	
}
