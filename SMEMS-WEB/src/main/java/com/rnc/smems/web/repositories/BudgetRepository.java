package com.rnc.smems.web.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.rnc.smems.web.entities.Budget;
import com.rnc.smems.web.entities.InStock;



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
		String str = "select t from %s t where t.id = "+id;
		String sql = String.format(str, Budget.class.getSimpleName());
		TypedQuery<Budget> query = entityManager.createQuery(sql, Budget.class);
		return query.getSingleResult();
	}
	
	public List<Budget> findByMaterial (long id) {
		String str = "select t from %s t where t.material.id = "+id;
		String sql = String.format(str, InStock.class.getSimpleName());
		TypedQuery<Budget> query = entityManager.createQuery(sql, Budget.class);
		return query.getResultList();
	}
	
	public List<Budget> findAll () {
		String str = "select t from %s t";
		String sql = String.format(str, Budget.class.getSimpleName());
		TypedQuery<Budget> query = entityManager.createQuery(sql, Budget.class);
		return query.getResultList();
	}
	
}

