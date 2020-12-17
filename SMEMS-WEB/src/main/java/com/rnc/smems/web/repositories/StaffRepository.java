package com.rnc.smems.web.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.rnc.smems.web.entities.Staff;

public class StaffRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void save (Staff staff) {
		entityManager.persist(staff);
	}
	
	public void update (Staff staff) {
		entityManager.merge(staff);
	}
	
	public void delete (Staff staff) {
		entityManager.remove(staff);
	}
	
	public Staff findByID (long id) {
		String str = "select t from %s t where t.id = "+id+"";
		String sql = String.format(str, Staff.class.getSimpleName());
		TypedQuery<Staff> query = entityManager.createQuery(sql, Staff.class);
		return query.getSingleResult();
	}
	
	public List<Staff> findAll() {
		String str = "select t from %s t where t.erase = false"; // select account from account;
		String sql = String.format(str, Staff.class.getSimpleName());
		TypedQuery<Staff> query = entityManager.createQuery(sql, Staff.class);
		return query.getResultList();
	}
	
}
