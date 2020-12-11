package com.rnc.smems.web.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.rnc.smems.web.entities.InStock;

public class InStockRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	public void save (InStock inStock) {
		entityManager.persist(inStock);
	}
	
	public void update (InStock inStock) {
		entityManager.merge(inStock);
	}
	
	public void delete (InStock inStock) {
		entityManager.remove(inStock);
	}
	
	public InStock findByID (long id) {
		String str = "select t from %s t where t.id = "+id;
		String sql = String.format(str, InStock.class.getSimpleName());
		TypedQuery<InStock> query = entityManager.createQuery(sql, InStock.class);
		return query.getSingleResult();
	}
	
	public List<InStock> findByMaterial (long id) {
		String str = "select t from %s t where t.material.id = "+id;
		String sql = String.format(str, InStock.class.getSimpleName());
		TypedQuery<InStock> query = entityManager.createQuery(sql, InStock.class);
		return query.getResultList();
	}
	
	public List<InStock> findAll () {
		String str = "select t from %s t";
		String sql = String.format(str, InStock.class.getSimpleName());
		TypedQuery<InStock> query = entityManager.createQuery(sql, InStock.class);
		return query.getResultList();
	}
	
}
