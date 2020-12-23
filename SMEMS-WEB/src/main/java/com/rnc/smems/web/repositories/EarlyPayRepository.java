package com.rnc.smems.web.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.rnc.smems.web.entities.EarlyPay;

public class EarlyPayRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void save (EarlyPay earlypay) {
		entityManager.persist(earlypay);
	}
	public void update (EarlyPay earlypay) {
		entityManager.merge(earlypay);
	}
	public void delete (EarlyPay earlypay) {
		entityManager.remove(earlypay);
	}
	public EarlyPay findByID (long id) {
		String str = "select t from %s t where t.id = " + id;
		String sql = String.format(str, EarlyPay.class.getSimpleName());
		TypedQuery<EarlyPay> query = entityManager.createQuery(sql, EarlyPay.class);
		return query.getSingleResult();
	}
	public List<EarlyPay> findAll(){
		String str = "select t from %s t where t.erase = false";
		String sql = String.format(str, EarlyPay.class.getSimpleName());
		TypedQuery<EarlyPay> query = entityManager.createQuery(sql, EarlyPay.class);
		return query.getResultList();
	}
}
