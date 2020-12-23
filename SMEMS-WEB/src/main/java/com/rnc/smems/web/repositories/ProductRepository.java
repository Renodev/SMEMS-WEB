package com.rnc.smems.web.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.rnc.smems.web.entities.Ingredient;
import com.rnc.smems.web.entities.Material;
import com.rnc.smems.web.entities.Product;

public class ProductRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	public void save (Product product) {
		entityManager.persist(product);
	}
	
	
	public void update (Product product) {
		entityManager.merge(product);
	}
	
	public void delete (Product product) {
		entityManager.remove(product);
	}
	
	
	public Product findByID (long id) {
		String str = "select t from %s t where t.id = " + id;
		String sql = String.format(str, Product.class.getSimpleName());
		TypedQuery<Product> query = entityManager.createQuery(sql, Product.class);
		return query.getSingleResult();
	}
	
	public List<Product>  findAll () {
		String str = "select t from %s t where t.erase = false";
		String sql = String.format(str, Product.class.getSimpleName());
		TypedQuery<Product> query = entityManager.createQuery(sql, Product.class);
		return query.getResultList();
	}
	
	
	
	
	
	

}
