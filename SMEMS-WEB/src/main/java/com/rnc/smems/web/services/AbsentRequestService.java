package com.rnc.smems.web.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;

import com.rnc.smems.web.entities.AbsentRequest;
import com.rnc.smems.web.repositories.AbsentRequestRepository;

/**
 * 
 * @author Thae Nandar Soe
 * 			18/12/2020
 *
 */

@LocalBean
@Stateless
public class AbsentRequestService {
	
	@Inject
	private AbsentRequestRepository absentRequestRepositories;
	
	public void save(AbsentRequest absentRequest) {
		if(absentRequest.getId() == 0) {
			absentRequestRepositories.save(absentRequest);
		}else 
			absentRequestRepositories.update(absentRequest);
	}
	
	public void delete(AbsentRequest absentRequest) {
		absentRequest.setErase(true);
		absentRequestRepositories.update(absentRequest);
		
	}
	
	public AbsentRequest findById(long id) {
		return absentRequestRepositories.findById(id);
	}
	
	public List<AbsentRequest> findAll(){
		return absentRequestRepositories.findAll();
	}

}
