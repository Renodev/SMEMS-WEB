package com.rnc.smems.web.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.rnc.smems.web.entities.Attendance;

public class AttendanceRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void save(Attendance attendance) {
		entityManager.persist(attendance);	
	}
	
	public void update(Attendance attendance) {
		entityManager.merge(attendance);
	}
	
	public void delete(Attendance attendance) {
		entityManager.remove(attendance);
		
	}
	
	public Attendance findByID(long id) {
		String str = "select t from %s t where t.id = "+id;
		String sql = String.format(str, Attendance.class.getSimpleName());
		TypedQuery<Attendance> query = entityManager.createQuery(sql, Attendance.class);
		return query.getSingleResult();
	}
	
	public List<Attendance> findAll(){
		String str = "select t from %s t";
		String sql = String.format(str, Attendance.class.getSimpleName());
		TypedQuery<Attendance> query = entityManager.createQuery(sql, Attendance.class);
		return query.getResultList();
	}

}