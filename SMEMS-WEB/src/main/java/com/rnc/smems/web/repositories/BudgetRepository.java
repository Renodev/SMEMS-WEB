package com.rnc.smems.web.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.rnc.smems.web.entities.Budget;

/**
 * @author Yadanar Myint Maw
 * @since 18-12-2020
 *  
 *  
 *  */

public class BudgetRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public void save (Budget budget) {
		entityManager.persist(budget);
	}
	
	public void update (Budget budget) {
		entityManager.merge(budget);
	}
	
	public void delete (Budget budget) {
		entityManager.remove(budget);
	}
	
	public Budget findByID (long id) {
		String str = "select t from %s t where t.id = " + id;
		String sql = String.format(str, Budget.class.getSimpleName());
		TypedQuery<Budget> query = entityManager.createQuery(sql, Budget.class);
		return query.getSingleResult();
	}
	
	public List<Budget> findAll () {
		String str = "select t from %s t where t.erase = false";
		String sql = String.format(str, Budget.class.getSimpleName());
		TypedQuery<Budget> query = entityManager.createQuery(sql, Budget.class);
		return query.getResultList();
	}
	
}