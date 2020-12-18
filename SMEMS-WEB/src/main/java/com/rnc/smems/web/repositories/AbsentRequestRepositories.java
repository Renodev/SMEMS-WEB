package com.rnc.smems.web.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.rnc.smems.web.entities.AbsentRequest;


/**
 * 
 * @author Thae Nandar Soe
 * 			18/12/2020
 *
 */
public class AbsentRequestRepositories {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public void save(AbsentRequest absentRequest) {
		entityManager.persist(absentRequest);
	}
	
	public void update(AbsentRequest absentRequest) {
		entityManager.merge(absentRequest);
	}
	
	public void delete(AbsentRequest absentRequest) {
		entityManager.remove(absentRequest);
	}
	
	public AbsentRequest findById(long id) {
		String str = "select t from %s t where t.id = " + id;
		String sql = String.format(str, AbsentRequest.class.getSimpleName());
		TypedQuery<AbsentRequest> query = entityManager.createQuery(sql, AbsentRequest.class);
		return query.getSingleResult();
	}
	
	public List<AbsentRequest> findAll(){
		String str = "select t from %s t where t.erase = false";
		String sql = String.format(str, AbsentRequest.class.getSimpleName());
		TypedQuery<AbsentRequest> query = entityManager.createQuery(sql, AbsentRequest.class);
		return query.getResultList();
	}
}
