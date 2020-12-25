package com.rnc.smems.web.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import com.rnc.smems.web.entities.OverTime;

public class OvertimeRepostitory {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	public void save(OverTime overTime) {
		entityManager.persist(overTime);
	}
	
	public void  update(OverTime overTime) {
		entityManager.merge(overTime);
		
	}
	
	public void delete(OverTime overTime) {
		entityManager.remove(overTime);
		
	}
	
	public OverTime findbyid(long id) {     // delete 
		String str = "select t from %s t where t.id = "+id;
		String sql = String.format(str, OverTime.class.getSimpleName());
		TypedQuery<OverTime> query = entityManager.createQuery(sql, OverTime.class);
		return query.getSingleResult();
	}
	
    public List<OverTime> finbystaff(long id){    //staff
		String str="select t from %s t  where t.staff.id "+id;
		String sql=String.format(str,OverTime.class.getSimpleName());
		TypedQuery<OverTime> query=entityManager.createQuery(sql,OverTime.class);
		return query.getResultList()	;
}
	
	
	 public  List<OverTime> findAll(){    //list
		String str="select  t from %s  t";
		String sql=String.format(str, OverTime.class.getSimpleName());
		TypedQuery<OverTime>query=entityManager.createQuery(sql,OverTime.class);
		return query.getResultList();
		
	}
	

}
