package com.rnc.smems.web.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.rnc.smems.web.entities.Customer;


public class CustomerRepository {
	@PersistenceContext
	private EntityManager entityManager;
	
	public void save (Customer customer) {
		entityManager.persist(customer);
	}
	
	public void update (Customer customer) {
		entityManager.merge(customer);
	}
	
	public void delete (Customer customer) {
		entityManager.remove(customer);
	}
	
	public Customer findByID (long id) {
		String str = "select t from %s t where t.id = "+id+"";
		String sql = String.format(str, Customer.class.getSimpleName());
		TypedQuery<Customer> query = entityManager.createQuery(sql, Customer.class);
		return query.getSingleResult();
	}
	
	public List<Customer> findAll() {
		String str = "select t from %s t"; 
		String sql = String.format(str, Customer.class.getSimpleName());
		TypedQuery<Customer> query = entityManager.createQuery(sql, Customer.class);
		return query.getResultList();
	}
	
	
}
