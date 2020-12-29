package com.rnc.smems.web.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.rnc.smems.web.entities.Job;

public class JobRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public void save (Job job) {
		entityManager.persist(job);
	}
	
	public void update (Job job) {
		entityManager.merge(job);
	}
	
	public void delete (Job job) {
		entityManager.remove(job);
	}
	
	public Job findByID (long id) {
		String str = "select t from %s t where t.id = "+id;
		String sql = String.format(str, Job.class.getSimpleName());
		TypedQuery<Job> query = entityManager.createQuery(sql, Job.class);
		return query.getSingleResult();
	}
	
	public List<Job> findByProduct(long id){   
		String str="select t from %s t  where t.product.id "+id;
		String sql=String.format(str,Job.class.getSimpleName());
		TypedQuery<Job> query=entityManager.createQuery(sql,Job.class);
		return query.getResultList();
	}
	
	public List<Job> findAll () {
		String str = "select t from %s t";
		String sql = String.format(str, Job.class.getSimpleName());
		TypedQuery<Job> query = entityManager.createQuery(sql, Job.class);
		return query.getResultList();
	}
	
	
}