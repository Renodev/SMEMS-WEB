package com.rnc.smems.web.repositories;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.rnc.smems.web.entities.InStock;
import com.rnc.smems.web.entities.Ingredient;

public class IngredientsRepository {
	
	@PersistenceContext
	private EntityManager entityManager;

	public void save (Ingredient ingredient) {
		entityManager.persist(ingredient);
	}
	
	public void update (Ingredient ingredient) {
		entityManager.merge(ingredient);
	}
	
	public void delete (Ingredient ingredient) {
		entityManager.remove(ingredient);
	}
	
	public Ingredient findByID (long id) {
		String str = "select t from %s t where t.id = "+id;
		String sql = String.format(str, Ingredient.class.getSimpleName());
		TypedQuery<Ingredient> query = entityManager.createQuery(sql, Ingredient.class);
		return query.getSingleResult();
	}
	
	public List<Ingredient> findByMaterial (long id) {
		String str = "select t from %s t where t.material.id = "+id;
		String sql = String.format(str, Ingredient.class.getSimpleName());
		TypedQuery<Ingredient> query = entityManager.createQuery(sql, Ingredient.class);
		return query.getResultList();
	}
	
	public List<Ingredient> findAll () {
		String str = "select t from %s t";
		String sql = String.format(str, Ingredient.class.getSimpleName());
		TypedQuery<Ingredient> query = entityManager.createQuery(sql, Ingredient.class);
		return query.getResultList();
	}
	
	public List<Ingredient> findByProduct (long id) {
		String str = "select t from %s t where t.product.id = "+id;
		String sql = String.format(str, Ingredient.class.getSimpleName());
		TypedQuery<Ingredient> query = entityManager.createQuery(sql, Ingredient.class);
		return query.getResultList();
	}
	
	
}
