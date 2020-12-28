package com.rnc.smems.web.repositories;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.rnc.smems.web.entities.InStock;
import com.rnc.smems.web.entities.Material;
import com.rnc.smems.web.entities.OverTime;

public class OverTimeRepository {
	

	@PersistenceContext
	private EntityManager entityManager;

	public void save (OverTime overtime) {
		entityManager.persist(overtime);
	}
	
	public void update (OverTime overtime) {
		entityManager.merge(overtime);
	}
	
	public void delete (OverTime overtime) {
		entityManager.remove(overtime);
	}
	
	public OverTime findByID (long id) {
		String str = "select t from %s t where t.id = "+id;
		String sql = String.format(str, OverTime.class.getSimpleName());
		TypedQuery<OverTime> query = entityManager.createQuery(sql, OverTime.class);
		return query.getSingleResult();
	}
	
	public List<OverTime> findAll () {
		String str = "select t from %s t";
		String sql = String.format(str, OverTime.class.getSimpleName());
		TypedQuery<OverTime> query = entityManager.createQuery(sql, OverTime.class);
		return query.getResultList();
	}
	
	public List<OverTime> findByStaff (long id) {
		String str = "select t from %s t where t.staff.id = "+id;
		String sql = String.format(str, OverTime.class.getSimpleName());
		TypedQuery<OverTime> query = entityManager.createQuery(sql, OverTime.class);
		return query.getResultList();
	}
	
	/*public List<OverTime>  findByMonth () {
		String Date = LocalDate.now().toString();
		String str = "select t from %s t where t.Date = " +Date;
		String sql = String.format(str, OverTime.class.getSimpleName());
		TypedQuery<OverTime> query = entityManager.createQuery(sql, OverTime.class);
		return query.getResultList();
	}
	*/
	
	
	
	
	
	
}
